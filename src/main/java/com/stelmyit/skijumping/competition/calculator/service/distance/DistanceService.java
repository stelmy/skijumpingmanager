package com.stelmyit.skijumping.competition.calculator.service.distance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DistanceService {

    @Autowired
    private DistanceHillValueService distanceHillValueService;

    public BigDecimal calculate(final BigDecimal distance, final int kPoint) {
        final BigDecimal baseDistanceValue = distanceHillValueService.getBaseDistancePoints(kPoint);
        final BigDecimal distanceMeterValue = distanceHillValueService.getMeterValue(kPoint);
        return (baseDistanceValue.add(distance.subtract(BigDecimal.valueOf(kPoint)).multiply(distanceMeterValue)));
    }
}
