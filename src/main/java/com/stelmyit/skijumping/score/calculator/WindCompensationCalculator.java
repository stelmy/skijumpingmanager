package com.stelmyit.skijumping.score.calculator;

import com.stelmyit.skijumping.competition.competition.model.Competition;
import com.stelmyit.skijumping.hill.model.Hill;
import com.stelmyit.skijumping.jump.jump.model.Jump;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.RoundingMode.DOWN;

@Service
public class WindCompensationCalculator {

    public BigDecimal calculate(final Jump jump, final Hill hill) {
        final float windCompensation = jump.getWindSpeed() * (hill.getHillSize() - 36) / 20;
        return BigDecimal.valueOf(windCompensation).setScale(1, DOWN);
    }
}
