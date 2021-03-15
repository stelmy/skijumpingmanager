package com.stelmyit.skijumping.note.service;

import com.stelmyit.skijumping.competition.model.Competition;
import com.stelmyit.skijumping.hill.model.Hill;
import com.stelmyit.skijumping.jump.model.Jump;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.RoundingMode.DOWN;

@Service
public class DistanceService {

    private final DistanceHillValueService distanceHillValueService;

    @Autowired
    public DistanceService(final DistanceHillValueService distanceHillValueService) {
        this.distanceHillValueService = distanceHillValueService;
    }

    public BigDecimal calculate(final Jump jump, final Competition competition) {
        final Hill hill = competition.getHill();
        final int kPoint = hill.getKpoint();
        final int baseDistanceValue = distanceHillValueService.getBaseDistancePoints(kPoint);
        final float distanceMeterValue = distanceHillValueService.getMeterValue(kPoint);
        final float result = baseDistanceValue + (jump.getDistance() - kPoint) * distanceMeterValue;
        return BigDecimal.valueOf(result).setScale(1, DOWN);
    }
}
