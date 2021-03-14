package com.stelmyit.skijumping.note.controller;

import com.stelmyit.skijumping.jump.dto.JumpDTO;
import com.stelmyit.skijumping.jump.model.Jump;
import com.stelmyit.skijumping.note.model.JumpNote;
import com.stelmyit.skijumping.note.service.JumpNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("jump")
public class JumpNoteController {

    private final JumpNoteService jumpNoteService;

    @Autowired
    public JumpNoteController(JumpNoteService jumpNoteService) {
        this.jumpNoteService = jumpNoteService;
    }

    @RequestMapping(value = "/official", method = POST)
    public JumpNote calculateOfficialJump(@RequestBody JumpDTO jump) {
        return jumpNoteService.calculateOfficialJump(jump);
    }

    @RequestMapping(value = "/training", method = POST)
    public JumpNote calculateTrainingJump(@RequestBody JumpDTO jump) {
        return jumpNoteService.calculateTrainingJump(jump);
    }

    @RequestMapping(value = "/local", method = POST)
    public JumpNote calculateLocalJump(@RequestBody JumpDTO jump) {
        return jumpNoteService.calculateLocalJump(jump);
    }

}
