package com.stelmyit.skijumping.competition.calculator.service.decorator;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class NoteDecorator implements Note {
    private Note note;

    @Override
    public BigDecimal calculateTotalPoints() {
        return note.calculateTotalPoints();
    }

    @Override
    public BigDecimal calculateDistancePoints() {
        return note.calculateDistancePoints();
    }

    @Override
    public BigDecimal calculateJuryPoints() {
        return note.calculateJuryPoints();
    }

    @Override
    public BigDecimal calculateAdditionPoints() {
        return note.calculateAdditionPoints();
    }
}
