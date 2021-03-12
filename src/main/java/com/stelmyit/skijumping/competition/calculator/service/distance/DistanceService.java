package com.stelmyit.skijumping.competition.calculator.service.distance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.RoundingMode.DOWN;

@Service
public class DistanceService {

    private final DistanceHillValueService distanceHillValueService;

    @Autowired
    public DistanceService(DistanceHillValueService distanceHillValueService) {
        this.distanceHillValueService = distanceHillValueService;
    }

    public BigDecimal calculate(final float distance, final int kPoint) {
        final int baseDistanceValue = distanceHillValueService.getBaseDistancePoints(kPoint);
        final float distanceMeterValue = distanceHillValueService.getMeterValue(kPoint);
        final float result = baseDistanceValue + (distance - kPoint) * distanceMeterValue;
        return BigDecimal.valueOf(result).setScale(1, DOWN);
    }
}
