package com.stelmyit.skijumping.competition.calculator.controller;

import com.stelmyit.skijumping.competition.calculator.dto.CompetitionJumperRoundResultDTO;
import com.stelmyit.skijumping.competition.calculator.model.CompetitionJumperNote;
import com.stelmyit.skijumping.competition.calculator.service.CompetitionJumperNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("competition")
public class CompetitionCalculatorController {

    @Autowired
    private CompetitionJumperNoteService competitionJumperNoteService;

    @RequestMapping(value = "/calculate", method = POST)
    public CompetitionJumperNote calculate(@RequestBody CompetitionJumperRoundResultDTO body) {
        return competitionJumperNoteService.calculate(body);
    }

}
