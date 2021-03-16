package com.stelmyit.skijumping.common.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class JumpScoreDTO {

    private BigDecimal distanceScore;
    private BigDecimal juryScore;
    private BigDecimal gateCompensation;
    private BigDecimal windCompensation;
    private BigDecimal totalScore;

}
