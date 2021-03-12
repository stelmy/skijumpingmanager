package com.stelmyit.skijumping.competition.calculator.controller;

import com.stelmyit.skijumping.competition.calculator.dto.Jump;
import com.stelmyit.skijumping.competition.calculator.model.JumpNote;
import com.stelmyit.skijumping.competition.calculator.service.jump.JumpNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("jump")
public class JumpController {

    private final JumpNoteService jumpNoteService;

    @Autowired
    public JumpController(JumpNoteService jumpNoteService) {
        this.jumpNoteService = jumpNoteService;
    }

    @RequestMapping(value = "/official", method = POST)
    public JumpNote calculateOfficialJump(@RequestBody Jump jump) {
        return jumpNoteService.calculateOfficialJump(jump);
    }

    @RequestMapping(value = "/training", method = POST)
    public JumpNote calculateTrainingJump(@RequestBody Jump jump) {
        return jumpNoteService.calculateTrainingJump(jump);
    }

    @RequestMapping(value = "/local", method = POST)
    public JumpNote calculateLocalJump(@RequestBody Jump jump) {
        return jumpNoteService.calculateLocalJump(jump);
    }

}
