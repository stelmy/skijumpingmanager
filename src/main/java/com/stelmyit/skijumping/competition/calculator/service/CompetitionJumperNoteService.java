package com.stelmyit.skijumping.competition.calculator.service;

import com.stelmyit.skijumping.competition.calculator.dto.CompetitionJumperRoundResultDTO;
import com.stelmyit.skijumping.competition.calculator.model.CompetitionJumperNote;
import com.stelmyit.skijumping.competition.calculator.model.CompetitionJumperRoundNote;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class CompetitionJumperNoteService {

    public CompetitionJumperNote calculate(CompetitionJumperRoundResultDTO body) {
        System.out.println(body);
        return CompetitionJumperNote.builder()
            .roundNotes(
                Arrays.asList(
                    CompetitionJumperRoundNote.builder()
                        .distanceNote(BigDecimal.valueOf(150))
                        .build(),
                    CompetitionJumperRoundNote.builder()
                        .distanceNote(BigDecimal.valueOf(148.5))
                        .build()
                )
            )
            .build();
    }
}
