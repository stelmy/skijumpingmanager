package com.stelmyit.skijumping.competition.calculator.service.decorator;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class NoteDecorator implements Note {
    private Note note;

    @Override
    public BigDecimal decorate() {
        return note.decorate();
    }
}
