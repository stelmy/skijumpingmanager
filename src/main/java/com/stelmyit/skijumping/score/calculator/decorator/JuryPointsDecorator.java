package com.stelmyit.skijumping.score.calculator.decorator;

import java.math.BigDecimal;

public class JuryPointsDecorator extends ScoreDecorator {
    private final BigDecimal juryPoints;

    public JuryPointsDecorator(final Score score, final BigDecimal juryPoints) {
        super(score);
        this.juryPoints = juryPoints;
    }

    @Override
    public BigDecimal calculateTotalPoints() {
        return super.calculateTotalPoints().add(calculateJuryPoints());
    }

    @Override
    public BigDecimal calculateJuryPoints() {
        return juryPoints;
    }

}
