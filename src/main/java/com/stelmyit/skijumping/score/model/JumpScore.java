package com.stelmyit.skijumping.score.model;

import com.stelmyit.skijumping.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
public class JumpScore extends BaseEntity {

    private BigDecimal distanceScore;
    private BigDecimal juryScore;
    private BigDecimal gateCompensation;
    private BigDecimal windCompensation;
    private BigDecimal totalScore;

}
