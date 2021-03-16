package com.stelmyit.skijumping.jump.jump.service;

import com.stelmyit.skijumping.jump.jump.dto.JumpDTO;
import com.stelmyit.skijumping.jump.jump.factory.JumpFactory;
import com.stelmyit.skijumping.jump.jump.model.Jump;
import com.stelmyit.skijumping.jump.jump.repository.JumpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JumpService {

    private final JumpRepository jumpRepository;
    private final JumpFactory jumpFactory;

    @Autowired
    public JumpService(final JumpRepository jumpRepository,
                       final JumpFactory jumpFactory) {
        this.jumpRepository = jumpRepository;
        this.jumpFactory = jumpFactory;
    }

    public Long add(JumpDTO jumpDTO) {
        final Jump jump = jumpFactory.createEntity(jumpDTO);
        final Jump savedJump = jumpRepository.save(jump);
        return savedJump.getId();
    }

}
