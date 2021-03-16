package com.stelmyit.skijumping.score.calculator;

import com.stelmyit.skijumping.hill.model.Hill;
import com.stelmyit.skijumping.jump.jump.model.Jump;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.RoundingMode.DOWN;

@Service
public class GateCompensationCalculator {

    private final DistanceHillValueCalculator distanceHillValueCalculator;

    @Autowired
    public GateCompensationCalculator(final DistanceHillValueCalculator distanceHillValueCalculator) {
        this.distanceHillValueCalculator = distanceHillValueCalculator;
    }

    public BigDecimal calculate(final Jump jump, final Hill hill, final int baseGate) {
        final float meterValue = distanceHillValueCalculator.getMeterValue(hill.getKpoint());
        final float gatePoints = 2.5f * (baseGate - jump.getGate()) * meterValue;
        return BigDecimal.valueOf(gatePoints).setScale(1, DOWN);
    }
}
