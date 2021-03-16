package com.stelmyit.skijumping.judge.model;

import com.stelmyit.skijumping.common.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@Entity
public class Judge extends Person {

}
