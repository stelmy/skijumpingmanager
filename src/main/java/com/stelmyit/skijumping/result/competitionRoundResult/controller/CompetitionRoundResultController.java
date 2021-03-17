package com.stelmyit.skijumping.result.competitionRoundResult.controller;

import com.stelmyit.skijumping.result.competitionRoundResult.dto.CompetitionRoundResultDTO;
import com.stelmyit.skijumping.result.competitionRoundResult.service.CompetitionRoundResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("roundResult")
public class CompetitionRoundResultController {

    private final CompetitionRoundResultService competitionRoundResultService;

    @Autowired
    public CompetitionRoundResultController(final CompetitionRoundResultService competitionRoundResultService) {
        this.competitionRoundResultService = competitionRoundResultService;
    }

    // TODO: should not be needed after competition round completing
    @RequestMapping(method = POST)
    public void completeRoundResults(@RequestParam Long roundId) {
        competitionRoundResultService.completeRound(roundId);
    }

    @RequestMapping(method = GET)
    public List<CompetitionRoundResultDTO> getAllResultByRoundId(@RequestParam Long roundId) {
        return competitionRoundResultService.getAllByRoundId(roundId);
    }

}
