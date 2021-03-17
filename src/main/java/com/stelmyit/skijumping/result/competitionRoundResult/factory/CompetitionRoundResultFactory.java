package com.stelmyit.skijumping.result.competitionRoundResult.factory;

import com.stelmyit.skijumping.competition.competitionRound.model.CompetitionRound;
import com.stelmyit.skijumping.competition.competitionRound.repository.CompetitionRoundRepository;
import com.stelmyit.skijumping.country.model.Country;
import com.stelmyit.skijumping.jump.jump.model.Jump;
import com.stelmyit.skijumping.jumper.model.Jumper;
import com.stelmyit.skijumping.result.competitionRoundResult.dto.CompetitionRoundResultDTO;
import com.stelmyit.skijumping.result.competitionRoundResult.model.CompetitionRoundResult;
import com.stelmyit.skijumping.score.model.JumpScore;
import com.stelmyit.skijumping.score.repository.JumpScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;
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

    public List<CompetitionRoundResultDTO> createDtos(List<CompetitionRoundResult> entities) {
        return entities.stream()
            .map(entity -> {
                final JumpScore jumpScore = entity.getJumpScore();
                final Jump jump = jumpScore.getJump();
                final Jumper jumper = jump.getJumper();
                final Country country = jumper.getCountry();
                return CompetitionRoundResultDTO.builder()
                    .position(entity.getPosition())
                    .firstName(jumper.getFirstName())
                    .lastName(jumper.getLastName())
                    .countryCode(country.getCode())
                    .distance(jump.getDistance())
                    .totalNote(jumpScore.getTotalScore())
                    .build();
            })
            .collect(toList());
    }

}
