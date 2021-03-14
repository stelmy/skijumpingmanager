package com.stelmyit.skijumping.note.decorator;

import java.math.BigDecimal;

public class WindCompensationDecorator extends NoteDecorator {
    private BigDecimal windCompensation;

    public WindCompensationDecorator(final Note note, final BigDecimal windCompensation) {
        super(note);
        this.windCompensation = windCompensation;
    }

    @Override
    public BigDecimal calculateTotalPoints() {
        return super.calculateTotalPoints().add(calculateAdditionalPoints());
    }

    @Override
    public BigDecimal calculateAdditionalPoints() {
        return super.calculateAdditionalPoints().add(windCompensation);
    }
}
