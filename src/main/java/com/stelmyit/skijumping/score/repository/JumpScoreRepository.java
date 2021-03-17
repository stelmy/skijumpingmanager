package com.stelmyit.skijumping.score.repository;

import com.stelmyit.skijumping.score.model.JumpScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface JumpScoreRepository extends JpaRepository<JumpScore, Long> {

    @Query("SELECT js FROM JumpScore js LEFT JOIN js.jump j WHERE j.id = ?1")
    JumpScore getByJumpId(Long jumpId);

    @Query("SELECT js FROM JumpScore js LEFT JOIN js.jump j LEFT JOIN j.competitionRound cr WHERE cr.id = ?1")
    List<JumpScore> getByCompetitionRoundId(Long competitionRoundId);
}
