package com.stelmyit.skijumping.jumper.model;

import com.stelmyit.skijumping.common.model.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
public class Jumper extends Person {
}
