package com.stelmyit.skijumping.competition.calculator.dto;

import java.math.BigDecimal;
import java.util.List;

public class CompetitionJumperRoundResultDTO {
    private Long competitionId;
    private BigDecimal distance;
    private int gate;
    private int windSpeed;
    private List<BigDecimal> juryNotes;
}
