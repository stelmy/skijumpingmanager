package com.stelmyit.skijumping.score.factory;

import com.stelmyit.skijumping.common.dto.JumpScoreDTO;
import com.stelmyit.skijumping.common.factory.CommonFactory;
import com.stelmyit.skijumping.jump.jump.model.Jump;
import com.stelmyit.skijumping.score.calculator.decorator.Score;
import com.stelmyit.skijumping.score.model.JumpScore;
import org.springframework.stereotype.Service;

@Service
public class JumpScoreFactory extends CommonFactory<JumpScore, JumpScoreDTO> {

    @Override
    public JumpScore createEntity(JumpScoreDTO jumpScoreDTO) {
        return null;
    }

    @Override
    public JumpScoreDTO createDTO(JumpScore jumpScore) {
        return JumpScoreDTO.builder()
            .jumpId(jumpScore.getJump().getId())
            .distanceScore(jumpScore.getDistanceScore())
            .juryScore(jumpScore.getJuryScore())
            .gateCompensation(jumpScore.getGateCompensation())
            .windCompensation(jumpScore.getWindCompensation())
            .totalScore(jumpScore.getTotalScore())
            .build();
    }

    public JumpScore createEntity(final Score score, final Jump jump) {
        return JumpScore.builder()
            .jump(jump)
            .distanceScore(score.calculateDistancePoints())
            .juryScore(score.calculateJuryPoints())
            .gateCompensation(score.calculateGatePoints())
            .windCompensation(score.calculateWindPoints())
            .totalScore(score.calculateTotalPoints())
            .build();
    }
}
