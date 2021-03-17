package com.stelmyit.skijumping.result.competitionRoundResult.repository;

import com.stelmyit.skijumping.result.competitionRoundResult.model.CompetitionRoundResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CompetitionRoundResultRepository extends JpaRepository<CompetitionRoundResult, Long> {

    @Query("SELECT round_result FROM CompetitionRoundResult round_result LEFT JOIN round_result.competitionRound AS round WHERE round.id = ?1")
    List<CompetitionRoundResult> getAllByRoundId(Long competitionRoundId);
}
