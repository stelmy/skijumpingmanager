package com.stelmyit.skijumping.city.model;

import com.stelmyit.skijumping.common.model.BaseDictionary;
import com.stelmyit.skijumping.country.model.Country;
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
public class City extends BaseDictionary {

    @ManyToOne
    private Country country;

}
