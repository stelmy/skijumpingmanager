package com.stelmyit.skijumping.competition.competitionRound.repository;

import com.stelmyit.skijumping.competition.competitionRound.model.CompetitionRound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CompetitionRoundRepository extends JpaRepository<CompetitionRound, Long> {
}
