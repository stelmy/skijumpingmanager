package com.stelmyit.skijumping.result.competitionResult.model;

import com.stelmyit.skijumping.common.model.BaseEntity;
import com.stelmyit.skijumping.competition.competition.model.Competition;
import com.stelmyit.skijumping.jumper.model.Jumper;
import com.stelmyit.skijumping.result.competitionRoundResult.model.CompetitionRoundResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
public class CompetitionResult extends BaseEntity {

    @OneToOne
    private Competition competition;

    @ManyToOne
    private Jumper jumper;

    @OneToMany
    private List<CompetitionRoundResult> roundResults;

}
