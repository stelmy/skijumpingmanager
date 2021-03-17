package com.stelmyit.skijumping.result.competitionRoundResult.factory;

import com.stelmyit.skijumping.competition.competitionRound.model.CompetitionRound;
import com.stelmyit.skijumping.competition.competitionRound.repository.CompetitionRoundRepository;
import com.stelmyit.skijumping.result.competitionRoundResult.dto.CompetitionRoundResultDTO;
import com.stelmyit.skijumping.result.competitionRoundResult.model.CompetitionRoundResult;
import com.stelmyit.skijumping.score.model.JumpScore;
import com.stelmyit.skijumping.score.repository.JumpScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        final List<CompetitionRoundResult> roundResults = jumpScores.stream()
            .map(jumpScore -> CompetitionRoundResult.builder()
                .jumpScore(jumpScore)
                .competitionRound(competitionRound).build())
            .collect(toList());
        addPositions(roundResults);
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

    private void addPositions(List<CompetitionRoundResult> roundResults) {
        roundResults.sort((roundResult1, roundResult2) -> {
            final BigDecimal totalScore1 = roundResult1.getJumpScore().getTotalScore();
            final BigDecimal totalScore2 = roundResult2.getJumpScore().getTotalScore();
            return totalScore2.compareTo(totalScore1);
        });

        for (int i = 0; i < roundResults.size(); i++) {
            roundResults.get(i).setPosition(i + 1);
        }
    }

}
