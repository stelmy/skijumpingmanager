package com.stelmyit.skijumping.competition.calculator.service.decorator;

import com.stelmyit.skijumping.competition.calculator.service.jurynotes.JuryNotesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class JuryNotes extends NoteDecorator {
    private List<BigDecimal> juryNotes;

    @Autowired
    private JuryNotesService juryNotesService;

    public JuryNotes(Note note, List<BigDecimal> juryNotes) {
        super(note);
        this.juryNotes = juryNotes;
    }

    @Override
    public BigDecimal decorate() {
        return super.decorate().add(decorateWithJuryNotes());
    }

    private BigDecimal decorateWithJuryNotes() {
        return juryNotesService.calculate(juryNotes);
    }

}
