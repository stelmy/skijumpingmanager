package com.stelmyit.skijumping.jump.jump.service;

import com.stelmyit.skijumping.common.service.CommonService;
import com.stelmyit.skijumping.jump.jump.dto.JumpDTO;
import com.stelmyit.skijumping.jump.jump.factory.JumpFactory;
import com.stelmyit.skijumping.jump.jump.model.Jump;
import com.stelmyit.skijumping.jump.jump.repository.JumpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JumpService extends CommonService<Jump, JumpDTO, JumpRepository, JumpFactory> {

    @Autowired
    public JumpService(final JumpRepository jumpRepository,
                       final JumpFactory jumpFactory) {
        super(jumpRepository, jumpFactory);
    }

}
