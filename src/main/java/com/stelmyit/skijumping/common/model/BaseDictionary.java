package com.stelmyit.skijumping.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public abstract class BaseDictionary extends BaseEntity {

    protected String name;
}
