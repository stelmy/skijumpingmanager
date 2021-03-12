package com.stelmyit.skijumping.competition.calculator.service.wind;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.RoundingMode.DOWN;

@Service
public class WindCompensationService {

    public BigDecimal calculate(final float windSpeed, final int hillSize) {
        final float windCompensation = windSpeed * (hillSize - 36) / 20;
        return BigDecimal.valueOf(windCompensation).setScale(1, DOWN);
    }
}
