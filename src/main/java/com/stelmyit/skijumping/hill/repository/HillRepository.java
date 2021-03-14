package com.stelmyit.skijumping.hill.repository;

import com.stelmyit.skijumping.hill.model.Hill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HillRepository extends JpaRepository<Hill, Long> {
}
