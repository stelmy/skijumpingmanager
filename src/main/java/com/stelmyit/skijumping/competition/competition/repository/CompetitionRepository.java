package com.stelmyit.skijumping.competition.competition.repository;

import com.stelmyit.skijumping.competition.competition.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    @Query("SELECT c FROM Competition c " +
        "LEFT JOIN c.competitionRounds AS cr " +
        "WHERE cr.id = ?1")
    Competition findByRoundId(Long competitionRoundId);
}
