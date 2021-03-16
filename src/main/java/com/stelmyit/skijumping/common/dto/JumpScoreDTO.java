package com.stelmyit.skijumping.common.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class JumpScoreDTO {

    private Long jumpId;
    private BigDecimal distanceScore;
    private BigDecimal juryScore;
    private BigDecimal gateCompensation;
    private BigDecimal windCompensation;
    private BigDecimal totalScore;

}
