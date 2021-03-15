package com.stelmyit.skijumping.jump.controller;

import com.stelmyit.skijumping.jump.JumpService;
import com.stelmyit.skijumping.jump.dto.JumpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("jump")
public class JumpController {

    private final JumpService jumpService;

    @Autowired
    public JumpController(JumpService jumpService) {
        this.jumpService = jumpService;
    }

    @RequestMapping(method = POST)
    public Long createJump(@RequestBody JumpDTO jump) {
        return jumpService.createJump(jump);
    }

}
