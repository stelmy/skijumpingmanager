package com.stelmyit.skijumping.score.model;

import com.stelmyit.skijumping.common.model.BaseEntity;
import com.stelmyit.skijumping.jump.jump.model.Jump;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JumpScore extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "jump_id")
    private Jump jump;

    @Column(name = "distance")
    private BigDecimal distanceScore;

    @Column(name = "jury")
    private BigDecimal juryScore;

    @Column(name = "gate")
    private BigDecimal gateCompensation;

    @Column(name = "wind")
    private BigDecimal windCompensation;

    @Column(name = "total")
    private BigDecimal totalScore;

}
