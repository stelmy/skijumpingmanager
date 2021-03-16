package com.stelmyit.skijumping.common.service;

import com.stelmyit.skijumping.common.factory.CommonFactory;
import com.stelmyit.skijumping.common.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class CommonService<Entity extends BaseEntity,
                           DTO,
                           Repository extends JpaRepository<Entity, Long>,
                           Factory extends CommonFactory<Entity, DTO>> {

    protected final Repository repository;
    protected final Factory factory;

    public CommonService(final Repository repository, final Factory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public Entity add(DTO dto) {
        final Entity entity = factory.createEntity(dto);
        return repository.save(entity);
    }

    public DTO get(Long id) {
        final Entity entity = repository.getOne(id);
        return factory.createDTO(entity);
    }

    public List<DTO> getAll() {
        final List<Entity> entities = repository.findAll();
        return factory.createDtos(entities);
    }

}
