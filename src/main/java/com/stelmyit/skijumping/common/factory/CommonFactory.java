package com.stelmyit.skijumping.common.factory;

import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class CommonFactory<Entity, DTO> {
    public abstract Entity createEntity(DTO dto);
    public abstract DTO createDTO(Entity entity);

    public List<Entity> createEntities(List<DTO> dtos) {
        return dtos.stream().map(this::createEntity).collect(toList());
    }

    public List<DTO> createDtos(List<Entity> entities) {
        return entities.stream().map(this::createDTO).collect(toList());
    }
}
