package com.stelmyit.skijumping.result.competitionRoundResult.model;

import com.stelmyit.skijumping.common.model.BaseEntity;
import com.stelmyit.skijumping.competition.competitionRound.model.CompetitionRound;
import com.stelmyit.skijumping.score.model.JumpScore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CompetitionRoundResult extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "round_id")
    private CompetitionRound competitionRound;

    @Column(name = "position")
    private int position;

    @OneToOne
    @JoinColumn(name = "score_id")
    private JumpScore jumpScore;

}
