package com.stelmyit.skijumping.competition.calculator.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CompetitionJumperRoundResultDTO {
    private int kPoint;
    private BigDecimal distance;
    private int gate;
    private int windSpeed;
    private List<BigDecimal> juryNotes;
}
