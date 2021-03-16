package com.stelmyit.skijumping.jump.jump.model;

import com.stelmyit.skijumping.common.model.BaseEntity;
import com.stelmyit.skijumping.competition.competitionRound.model.CompetitionRound;
import com.stelmyit.skijumping.jump.juryNote.model.JuryNote;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

import static org.hibernate.annotations.CascadeType.ALL;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Jump extends BaseEntity {

    @ManyToOne
    private CompetitionRound competitionRound;

    private float distance;
    private int gate;
    private float windSpeed;

    @OneToMany
    @Cascade(ALL)
    private List<JuryNote> juryNotes;
}
