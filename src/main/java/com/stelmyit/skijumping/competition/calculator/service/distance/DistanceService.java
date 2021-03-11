package com.stelmyit.skijumping.competition.calculator.service.distance;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DistanceService {

    public BigDecimal calculate(final BigDecimal distance) {
        return (BigDecimal.valueOf(120).add(distance.subtract(BigDecimal.valueOf(120)).multiply(BigDecimal.valueOf(1.8))));
    }
}
