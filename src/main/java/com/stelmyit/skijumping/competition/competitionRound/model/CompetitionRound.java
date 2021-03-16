package com.stelmyit.skijumping.competition.competitionRound.model;

import com.stelmyit.skijumping.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CompetitionRound extends BaseEntity {

    private int number;
    private int baseGate;

    @Enumerated(EnumType.STRING)
    private CompetitionRoundType type;

}
