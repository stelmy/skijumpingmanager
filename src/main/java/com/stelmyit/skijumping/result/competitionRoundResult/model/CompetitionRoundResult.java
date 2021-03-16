package com.stelmyit.skijumping.result.competitionRoundResult.model;

import com.stelmyit.skijumping.common.model.BaseEntity;
import com.stelmyit.skijumping.competition.competitionRound.model.CompetitionRound;
import com.stelmyit.skijumping.score.model.JumpScore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
public class CompetitionRoundResult extends BaseEntity {

    @OneToOne
    private CompetitionRound competitionRound;

    @OneToOne
    private JumpScore jumpScore;
}
