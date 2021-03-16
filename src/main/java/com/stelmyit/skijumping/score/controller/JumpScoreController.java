package com.stelmyit.skijumping.score.controller;

import com.stelmyit.skijumping.common.dto.JumpScoreDTO;
import com.stelmyit.skijumping.score.service.JumpScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("score")
public class JumpScoreController {

    private final JumpScoreService jumpScoreService;

    @Autowired
    public JumpScoreController(JumpScoreService jumpService) {
        this.jumpScoreService = jumpService;
    }

    @RequestMapping(method = GET)
    public JumpScoreDTO getJump(@RequestParam Long jumpId) {
        return jumpScoreService.getScoreByJumpId(jumpId);
    }
}
