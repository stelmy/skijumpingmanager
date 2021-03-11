package com.stelmyit.skijumping.competition.calculator.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CompetitionJumperNote {
    private Long jumperId;
    private List<CompetitionJumperRoundNote> roundNotes;
    private BigDecimal totalNote;
}
