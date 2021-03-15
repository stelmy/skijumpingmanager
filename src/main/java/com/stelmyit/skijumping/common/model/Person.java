package com.stelmyit.skijumping.common.model;

import com.stelmyit.skijumping.country.model.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.ManyToOne;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Person extends BaseEntity {

    protected String firstName;
    protected String lastName;

    @ManyToOne
    protected Country country;
}
