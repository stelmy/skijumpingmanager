package com.stelmyit.skijumping.score.calculator.decorator;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class ScoreDecorator implements Score {
    private final Score score;

    @Override
    public BigDecimal calculateTotalPoints() {
        return score.calculateTotalPoints();
    }

    @Override
    public BigDecimal calculateDistancePoints() {
        return score.calculateDistancePoints();
    }

    @Override
    public BigDecimal calculateJuryPoints() {
        return score.calculateJuryPoints();
    }

    @Override
    public BigDecimal calculateGatePoints() {
        return score.calculateGatePoints();
    }

    @Override
    public BigDecimal calculateWindPoints() {
        return score.calculateWindPoints();
    }

}
