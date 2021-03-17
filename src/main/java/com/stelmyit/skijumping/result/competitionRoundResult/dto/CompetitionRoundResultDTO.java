package com.stelmyit.skijumping.result.competitionRoundResult.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CompetitionRoundResultDTO {
    private int position;
    private String firstName;
    private String lastName;
    private String countryCode;
    private float distance;
    private BigDecimal totalNote;
}
