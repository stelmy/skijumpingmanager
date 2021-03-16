package com.stelmyit.skijumping.hill.model;

import com.stelmyit.skijumping.city.model.City;
import com.stelmyit.skijumping.common.model.BaseDictionary;
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
public class Hill extends BaseDictionary {

    @ManyToOne
    private City city;

    private int kpoint;
    private int hillSize;
}
