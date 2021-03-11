package com.stelmyit.skijumping.competition.calculator.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CompetitionJumperRoundNote {
    private BigDecimal distanceNote;
    private BigDecimal juryNote;
    private BigDecimal additionalNote;
    private BigDecimal totalNote;
}
