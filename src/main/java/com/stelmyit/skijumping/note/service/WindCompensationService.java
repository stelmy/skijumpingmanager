package com.stelmyit.skijumping.note.service;

import com.stelmyit.skijumping.competition.model.Competition;
import com.stelmyit.skijumping.hill.model.Hill;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.RoundingMode.DOWN;

@Service
public class WindCompensationService {

    public BigDecimal calculate(final float windSpeed, final Competition competition) {
        final Hill hill = competition.getHill();
        final float windCompensation = windSpeed * (hill.getHillSize() - 36) / 20;
        return BigDecimal.valueOf(windCompensation).setScale(1, DOWN);
    }
}
