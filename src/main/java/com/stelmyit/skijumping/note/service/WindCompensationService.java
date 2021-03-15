package com.stelmyit.skijumping.note.service;

import com.stelmyit.skijumping.competition.model.Competition;
import com.stelmyit.skijumping.hill.model.Hill;
import com.stelmyit.skijumping.jump.model.Jump;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.RoundingMode.DOWN;

@Service
public class WindCompensationService {

    public BigDecimal calculate(final Jump jump, final Competition competition) {
        final Hill hill = competition.getHill();
        final float windCompensation = jump.getWindSpeed() * (hill.getHillSize() - 36) / 20;
        return BigDecimal.valueOf(windCompensation).setScale(1, DOWN);
    }
}
