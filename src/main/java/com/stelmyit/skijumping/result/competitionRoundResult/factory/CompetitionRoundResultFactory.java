package com.stelmyit.skijumping.result.competitionRoundResult.factory;

import com.stelmyit.skijumping.competition.competitionRound.model.CompetitionRound;
import com.stelmyit.skijumping.competition.competitionRound.repository.CompetitionRoundRepository;
import com.stelmyit.skijumping.result.competitionRoundResult.dto.CompetitionRoundResultDTO;
import com.stelmyit.skijumping.result.competitionRoundResult.model.CompetitionRoundResult;
import com.stelmyit.skijumping.score.model.JumpScore;
import com.stelmyit.skijumping.score.repository.JumpScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CompetitionRoundResultFactory {

    private final CompetitionRoundRepository competitionRoundRepository;
    private final JumpScoreRepository jumpScoreRepository;

    @Autowired
    public CompetitionRoundResultFactory(final CompetitionRoundRepository competitionRoundRepository,
                                         final JumpScoreRepository jumpScoreRepository) {
        this.competitionRoundRepository = competitionRoundRepository;
        this.jumpScoreRepository = jumpScoreRepository;
    }

    // TODO: add unit test
    public List<CompetitionRoundResult> createResults(Long competitionRoundId) {
        final List<JumpScore> jumpScores = jumpScoreRepository.getByCompetitionRoundId(competitionRoundId);
        final CompetitionRound competitionRound = competitionRoundRepository.getOne(competitionRoundId);
        jumpScores.sort((score1, score2) -> score2.getTotalScore().compareTo(score1.getTotalScore()));
        final List<CompetitionRoundResult> roundResults = new ArrayList<>();

        int position = 1;
        for (JumpScore jumpScore : jumpScores) {
            roundResults.add(CompetitionRoundResult.builder()
                .jumpScore(jumpScore)
                .competitionRound(competitionRound)
                .position(position)
                .build());
            position++;
        }
        return roundResults;
    }

    // TODO: add unit test
    public List<CompetitionRoundResultDTO> createDtos(List<CompetitionRoundResult> entities) {
        return entities.stream()
            .map(entity -> CompetitionRoundResultDTO.builder()
                .position(entity.getPosition())
                .firstName(entity.getJumpScore().getJump().getJumper().getFirstName())
                .lastName(entity.getJumpScore().getJump().getJumper().getLastName())
                .countryCode(entity.getJumpScore().getJump().getJumper().getCountry().getCode())
                .distance(entity.getJumpScore().getJump().getDistance())
                .totalNote(entity.getJumpScore().getTotalScore())
                .build())
            .collect(toList());
    }

}
