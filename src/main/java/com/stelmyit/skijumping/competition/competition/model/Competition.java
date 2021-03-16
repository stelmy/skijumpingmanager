package com.stelmyit.skijumping.competition.competition.model;

import com.stelmyit.skijumping.common.model.BaseEntity;
import com.stelmyit.skijumping.competition.competitionRound.model.CompetitionRound;
import com.stelmyit.skijumping.hill.model.Hill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Competition extends BaseEntity {

    @ManyToOne
    private Hill hill;

    @OneToMany
    private List<CompetitionRound> competitionRounds;

}
