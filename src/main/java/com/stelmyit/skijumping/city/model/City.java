package com.stelmyit.skijumping.city.model;

import com.stelmyit.skijumping.common.model.BaseDictionary;
import com.stelmyit.skijumping.country.model.Country;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
public class City extends BaseDictionary {

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

}
