package com.stelmyit.skijumping.result.competitionResult.repository;

import com.stelmyit.skijumping.result.competitionResult.model.CompetitionResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CompetitionResultRepository extends JpaRepository<CompetitionResult, Long> {
}
