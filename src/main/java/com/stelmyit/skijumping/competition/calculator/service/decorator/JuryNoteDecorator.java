package com.stelmyit.skijumping.competition.calculator.service.decorator;

import java.math.BigDecimal;

public class JuryNoteDecorator extends NoteDecorator {
    private BigDecimal juryNote;

    public JuryNoteDecorator(final Note note, final BigDecimal juryNote) {
        super(note);
        this.juryNote = juryNote;
    }

    @Override
    public BigDecimal calculateTotalPoints() {
        return super.calculateTotalPoints().add(calculateJuryPoints());
    }

    @Override
    public BigDecimal calculateJuryPoints() {
        return juryNote;
    }

}
