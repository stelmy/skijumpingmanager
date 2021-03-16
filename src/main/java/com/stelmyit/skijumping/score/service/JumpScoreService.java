package com.stelmyit.skijumping.score.service;

import com.stelmyit.skijumping.common.dto.JumpScoreDTO;
import com.stelmyit.skijumping.jump.jump.model.Jump;
import com.stelmyit.skijumping.score.calculator.JumpScoreCalculator;
import com.stelmyit.skijumping.score.factory.JumpScoreFactory;
import com.stelmyit.skijumping.score.model.JumpScore;
import com.stelmyit.skijumping.score.repository.JumpScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JumpScoreService {

    private final JumpScoreRepository jumpScoreRepository;
    private final JumpScoreFactory jumpScoreFactory;
    private final JumpScoreCalculator jumpScoreCalculator;

    @Autowired
    public JumpScoreService(final JumpScoreRepository jumpScoreRepository,
                            final JumpScoreFactory jumpScoreFactory,
                            final JumpScoreCalculator jumpScoreCalculator) {
        this.jumpScoreRepository = jumpScoreRepository;
        this.jumpScoreFactory = jumpScoreFactory;
        this.jumpScoreCalculator = jumpScoreCalculator;
    }

    public void addScoreForJump(Jump jump) {
        final JumpScore score = jumpScoreCalculator.calculateJumpScore(jump);
        jumpScoreRepository.save(score);
    }

    public JumpScoreDTO getScoreByJumpId(Long jumpId) {
        final JumpScore jumpScore = jumpScoreRepository.getByJumpId(jumpId);
        return jumpScoreFactory.createDTO(jumpScore);
    }

}
