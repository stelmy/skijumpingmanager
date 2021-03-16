package com.stelmyit.skijumping.score.calculator.decorator;

import java.math.BigDecimal;

public class DistancePointsDecorator extends ScoreDecorator {
    private final BigDecimal distancePoints;

    public DistancePointsDecorator(final Score score, final BigDecimal distancePoints) {
        super(score);
        this.distancePoints = distancePoints;
    }

    @Override
    public BigDecimal calculateTotalPoints() {
        return super.calculateTotalPoints().add(calculateDistancePoints());
    }

    @Override
    public BigDecimal calculateDistancePoints() {
        return distancePoints;
    }
}
