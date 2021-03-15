package com.stelmyit.skijumping.judge.repository;

import com.stelmyit.skijumping.judge.model.Judge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface JudgeRepository extends JpaRepository<Judge, Long> {
}
