package com.stelmyit.skijumping.score.factory;

import com.stelmyit.skijumping.common.dto.JumpScoreDTO;
import com.stelmyit.skijumping.score.calculator.decorator.Score;
import com.stelmyit.skijumping.score.model.JumpScore;
import org.springframework.stereotype.Service;

@Service
public class JumpScoreFactory {

    public JumpScore createEntity(JumpScoreDTO jumpScoreDTO) {
        return JumpScore.builder()
            .distanceScore(jumpScoreDTO.getDistanceScore())
            .juryScore(jumpScoreDTO.getJuryScore())
            .gateCompensation(jumpScoreDTO.getGateCompensation())
            .windCompensation(jumpScoreDTO.getWindCompensation())
            .totalScore(jumpScoreDTO.getTotalScore())
            .build();
    }

    public JumpScore createEntity(final Score score) {
        return JumpScore.builder()
            .distanceScore(score.calculateDistancePoints())
            .juryScore(score.calculateJuryPoints())
            .gateCompensation(score.calculateGatePoints())
            .windCompensation(score.calculateWindPoints())
            .totalScore(score.calculateTotalPoints())
            .build();
    }
}
