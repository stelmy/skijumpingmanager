package com.stelmyit.skijumping.judge.controller;

import com.stelmyit.skijumping.judge.dto.JudgeDTO;
import com.stelmyit.skijumping.judge.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("judge")
public class JudgeController {

    private final JudgeService judgeService;

    @Autowired
    public JudgeController(final JudgeService judgeService) {
        this.judgeService = judgeService;
    }

    @RequestMapping(method = GET)
    public List<JudgeDTO> getAll() {
        return judgeService.getAll();
    }
}
