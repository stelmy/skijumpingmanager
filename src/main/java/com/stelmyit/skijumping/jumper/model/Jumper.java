package com.stelmyit.skijumping.jumper.model;

import com.stelmyit.skijumping.common.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Jumper extends Person {

    @Column(name = "fis_code")
    private String fisCode;
}
