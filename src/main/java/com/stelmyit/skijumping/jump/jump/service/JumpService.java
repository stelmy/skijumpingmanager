package com.stelmyit.skijumping.jump.jump.service;

import com.stelmyit.skijumping.common.service.CommonService;
import com.stelmyit.skijumping.jump.jump.dto.JumpDTO;
import com.stelmyit.skijumping.jump.jump.factory.JumpFactory;
import com.stelmyit.skijumping.jump.jump.model.Jump;
import com.stelmyit.skijumping.jump.jump.repository.JumpRepository;
import com.stelmyit.skijumping.score.service.JumpScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JumpService extends CommonService<Jump, JumpDTO, JumpRepository, JumpFactory> {

    private final JumpScoreService jumpScoreService;

    @Autowired
    public JumpService(final JumpRepository jumpRepository,
                       final JumpFactory jumpFactory,
                       final JumpScoreService jumpScoreService) {
        super(jumpRepository, jumpFactory);
        this.jumpScoreService = jumpScoreService;
    }

    @Override
    public Jump add(JumpDTO jumpDTO) {
        final Jump jump = super.add(jumpDTO);
        jumpScoreService.addScoreForJump(jump);
        return jump;
    }

}
