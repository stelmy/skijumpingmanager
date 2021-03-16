package com.stelmyit.skijumping.jump.juryNote.model;

import com.stelmyit.skijumping.common.model.BaseEntity;
import com.stelmyit.skijumping.judge.model.Judge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JuryNote extends BaseEntity {

    @ManyToOne
    private Judge judge;

    private float note;
}
