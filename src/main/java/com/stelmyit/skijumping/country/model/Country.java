package com.stelmyit.skijumping.country.model;

import com.stelmyit.skijumping.common.model.BaseDictionary;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
public class Country extends BaseDictionary {
    private String name;
}
