package com.stelmyit.skijumping.competition.calculator.service;

import com.stelmyit.skijumping.competition.calculator.dto.CompetitionJumperRoundResultDTO;
import com.stelmyit.skijumping.competition.calculator.model.JumpNote;
import com.stelmyit.skijumping.competition.calculator.service.decorator.DistanceNoteDecorator;
import com.stelmyit.skijumping.competition.calculator.service.decorator.JuryNoteDecorator;
import com.stelmyit.skijumping.competition.calculator.service.decorator.NoteDecorator;
import com.stelmyit.skijumping.competition.calculator.service.decorator.NoteImpl;
import com.stelmyit.skijumping.competition.calculator.service.distance.DistanceService;
import com.stelmyit.skijumping.competition.calculator.service.jurynotes.JuryNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CompetitionJumperNoteService {

    @Autowired
    private DistanceService distanceService;

    @Autowired
    private JuryNotesService juryNotesService;

    public JumpNote calculate(final CompetitionJumperRoundResultDTO body) {
        NoteDecorator note = new NoteDecorator(new NoteImpl());
        note = decorateWithDistancePoints(note, body.getDistance(), body.getKpoint());
        note = decorateWithJuryPoints(note, body.getJuryNotes());

        return JumpNote.builder()
            .distanceNote(note.calculateDistancePoints())
            .juryNote(note.calculateJuryPoints())
            .additionalNote(note.calculateAdditionPoints())
            .totalNote(note.calculateTotalPoints())
            .build();
   }

   private NoteDecorator decorateWithDistancePoints(final NoteDecorator note, final BigDecimal distance, final int kPoint) {
       final BigDecimal distancePoints = distanceService.calculate(distance, kPoint);
       return new DistanceNoteDecorator(note, distancePoints);
   }

    private NoteDecorator decorateWithJuryPoints(final NoteDecorator note, final List<BigDecimal> juryNotes) {
        final BigDecimal juryNote = juryNotesService.calculate(juryNotes);
        return new JuryNoteDecorator(note, juryNote);
    }
}
