package com.stelmyit.skijumping.competition.model;

import com.stelmyit.skijumping.common.model.BaseEntity;
import com.stelmyit.skijumping.competitionRound.model.CompetitionRound;
import com.stelmyit.skijumping.hill.model.Hill;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
public class Competition extends BaseEntity {

    @ManyToOne
    private Hill hill;

    @OneToMany
    private List<CompetitionRound> competitionRounds;

    public CompetitionRound getCompetitionRound(Long competitionRoundId) {
        return competitionRounds.stream()
            .filter(competitionRound -> Objects.equals(competitionRound.getId(), competitionRoundId))
            .findFirst()
            .orElse(CompetitionRound.builder().build());
    }

}
