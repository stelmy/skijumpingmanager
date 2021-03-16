package com.stelmyit.skijumping.score.calculator.decorator;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public class ScoreImpl implements Score {

    @Override
    public BigDecimal calculateTotalPoints() {
        return ZERO;
    }

    @Override
    public BigDecimal calculateDistancePoints() {
        return ZERO;
    }

    @Override
    public BigDecimal calculateJuryPoints() {
        return ZERO;
    }

    @Override
    public BigDecimal calculateGatePoints() {
        return ZERO;
    }

    @Override
    public BigDecimal calculateWindPoints() {
        return ZERO;
    }

}
