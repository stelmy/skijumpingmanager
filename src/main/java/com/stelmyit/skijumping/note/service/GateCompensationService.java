package com.stelmyit.skijumping.note.service;

import com.stelmyit.skijumping.competition.model.Competition;
import com.stelmyit.skijumping.competitionRound.model.CompetitionRound;
import com.stelmyit.skijumping.hill.model.Hill;
import com.stelmyit.skijumping.jump.model.Jump;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.RoundingMode.DOWN;

@Service
public class GateCompensationService {

    private final DistanceHillValueService distanceHillValueService;

    @Autowired
    public GateCompensationService(final DistanceHillValueService distanceHillValueService) {
        this.distanceHillValueService = distanceHillValueService;
    }

    public BigDecimal calculate(final Jump jump,
                                final Competition competition) {
        final Hill hill = competition.getHill();
        final Long competitionRoundId = jump.getCompetitionRound().getId();
        final CompetitionRound competitionRound = competition.getCompetitionRound(competitionRoundId);
        final float meterValue = distanceHillValueService.getMeterValue(hill.getKpoint());
        final float gatePoints = 2.5f * (competitionRound.getBaseGate() - jump.getGate()) * meterValue;
        return BigDecimal.valueOf(gatePoints).setScale(1, DOWN);
    }
}
