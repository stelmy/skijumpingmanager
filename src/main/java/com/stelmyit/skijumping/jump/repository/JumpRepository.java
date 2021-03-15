package com.stelmyit.skijumping.jump.repository;

import com.stelmyit.skijumping.jump.model.Jump;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface JumpRepository extends JpaRepository<Jump, Long> {
}
