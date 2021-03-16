package com.stelmyit.skijumping.competition.competitionRound.model;

import com.stelmyit.skijumping.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

import static javax.persistence.EnumType.STRING;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CompetitionRound extends BaseEntity {

    private int number;
    private int baseGate;

    @Enumerated(STRING)
    private CompetitionRoundType type;

}
