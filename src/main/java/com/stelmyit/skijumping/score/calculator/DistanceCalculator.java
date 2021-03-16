package com.stelmyit.skijumping.score.calculator;

import com.stelmyit.skijumping.hill.model.Hill;
import com.stelmyit.skijumping.jump.jump.model.Jump;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.RoundingMode.DOWN;

@Service
public class DistanceCalculator {

    private final DistanceHillValueCalculator distanceHillValueCalculator;

    @Autowired
    public DistanceCalculator(final DistanceHillValueCalculator distanceHillValueCalculator) {
        this.distanceHillValueCalculator = distanceHillValueCalculator;
    }

    public BigDecimal calculate(final Jump jump, final Hill hill) {
        final int kPoint = hill.getKpoint();
        final int baseDistanceValue = distanceHillValueCalculator.getBaseDistancePoints(kPoint);
        final float distanceMeterValue = distanceHillValueCalculator.getMeterValue(kPoint);
        final float result = baseDistanceValue + (jump.getDistance() - kPoint) * distanceMeterValue;
        return BigDecimal.valueOf(result).setScale(1, DOWN);
    }
}
