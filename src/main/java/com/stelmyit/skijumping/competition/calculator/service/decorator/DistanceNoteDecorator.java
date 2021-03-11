package com.stelmyit.skijumping.competition.calculator.service.decorator;

import java.math.BigDecimal;

public class DistanceNoteDecorator extends NoteDecorator {
    private BigDecimal distancePoints;

    public DistanceNoteDecorator(final Note note, final BigDecimal distancePoints) {
        super(note);
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
