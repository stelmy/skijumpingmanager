package com.stelmyit.skijumping.jump.jump.model;

import com.stelmyit.skijumping.common.model.BaseEntity;
import com.stelmyit.skijumping.competition.competitionRound.model.CompetitionRound;
import com.stelmyit.skijumping.jump.juryNote.model.JuryNote;
import com.stelmyit.skijumping.jumper.model.Jumper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
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
    @JoinColumn(name = "jumper_id")
    private Jumper jumper;

    @ManyToOne
    @JoinColumn(name = "competition_round_id")
    private CompetitionRound competitionRound;

    @Column(name = "distance", precision = 4, scale = 1)
    private float distance;

    @Column(name = "gate", precision = 2)
    private int gate;

    @Column(name = "wind_speed", precision = 2, scale = 1)
    private float windSpeed;

    @OneToMany
    @Cascade(ALL)
    private List<JuryNote> juryNotes;
}
