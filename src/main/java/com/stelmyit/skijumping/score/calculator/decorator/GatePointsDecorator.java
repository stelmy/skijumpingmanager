package com.stelmyit.skijumping.score.calculator.decorator;

import java.math.BigDecimal;

public class GatePointsDecorator extends ScoreDecorator {
    private final BigDecimal gatePoints;

    public GatePointsDecorator(final Score score, final BigDecimal gatePoints) {
        super(score);
        this.gatePoints = gatePoints;
    }

    @Override
    public BigDecimal calculateTotalPoints() {
        return super.calculateTotalPoints().add(calculateGatePoints());
    }

    @Override
    public BigDecimal calculateGatePoints() {
        return super.calculateGatePoints().add(gatePoints);
    }
}
