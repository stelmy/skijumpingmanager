package com.stelmyit.skijumping.jump.jump.controller;

import com.stelmyit.skijumping.jump.jump.dto.JumpDTO;
import com.stelmyit.skijumping.jump.jump.service.JumpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
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
        return jumpService.add(jump);
    }

    @RequestMapping(method = GET)
    public JumpDTO getJump(@RequestParam Long jumpId) {
        return jumpService.get(jumpId);
    }
}
