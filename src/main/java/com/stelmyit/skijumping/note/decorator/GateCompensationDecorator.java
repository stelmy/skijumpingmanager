package com.stelmyit.skijumping.note.decorator;

import java.math.BigDecimal;

public class GateCompensationDecorator extends NoteDecorator {
    private BigDecimal gateCompensation;

    public GateCompensationDecorator(final Note note, final BigDecimal gateCompensation) {
        super(note);
        this.gateCompensation = gateCompensation;
    }

    @Override
    public BigDecimal calculateTotalPoints() {
        return super.calculateTotalPoints().add(calculateAdditionalPoints());
    }

    @Override
    public BigDecimal calculateAdditionalPoints() {
        return super.calculateAdditionalPoints().add(gateCompensation);
    }
}
