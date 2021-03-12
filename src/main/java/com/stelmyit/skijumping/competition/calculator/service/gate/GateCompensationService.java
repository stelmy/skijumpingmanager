package com.stelmyit.skijumping.competition.calculator.service.gate;

import com.stelmyit.skijumping.competition.calculator.service.distance.DistanceHillValueService;
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

    public BigDecimal calculate(final int gate, final int baseGate, final int kPoint) {
        final float meterValue = distanceHillValueService.getMeterValue(kPoint);
        final float gatePoints = 2.5f * (baseGate - gate) * meterValue;
        return BigDecimal.valueOf(gatePoints).setScale(1, DOWN);
    }
}
