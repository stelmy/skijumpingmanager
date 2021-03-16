package com.stelmyit.skijumping.score.calculator.decorator;

import java.math.BigDecimal;

public class WindPointsDecorator extends ScoreDecorator {
    private final BigDecimal windPoints;

    public WindPointsDecorator(final Score score, final BigDecimal windPoints) {
        super(score);
        this.windPoints = windPoints;
    }

    @Override
    public BigDecimal calculateTotalPoints() {
        return super.calculateTotalPoints().add(calculateWindPoints());
    }

    @Override
    public BigDecimal calculateWindPoints() {
        return super.calculateWindPoints().add(windPoints);
    }
}
