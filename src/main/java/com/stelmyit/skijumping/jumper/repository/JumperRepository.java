package com.stelmyit.skijumping.jumper.repository;

import com.stelmyit.skijumping.jumper.model.Jumper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface JumperRepository extends JpaRepository<Jumper, Long> {
}
