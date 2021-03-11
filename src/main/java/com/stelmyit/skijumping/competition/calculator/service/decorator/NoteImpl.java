package com.stelmyit.skijumping.competition.calculator.service.decorator;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public class NoteImpl implements Note {

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
    public BigDecimal calculateAdditionPoints() {
        return ZERO;
    }
}
