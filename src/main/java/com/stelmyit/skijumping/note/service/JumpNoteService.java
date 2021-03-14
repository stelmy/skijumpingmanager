package com.stelmyit.skijumping.note.service;

import com.stelmyit.skijumping.competition.model.Competition;
import com.stelmyit.skijumping.competition.repository.CompetitionRepository;
import com.stelmyit.skijumping.jump.dto.JumpDTO;
import com.stelmyit.skijumping.note.decorator.*;
import com.stelmyit.skijumping.note.factory.JumpNoteFactory;
import com.stelmyit.skijumping.note.model.JumpNote;
import com.stelmyit.skijumping.note.model.JumpNoteComposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class JumpNoteService {

    private final DistanceService distanceService;
    private final JuryNotesService juryNotesService;
    private final WindCompensationService windCompensationService;
    private final GateCompensationService gateCompensationService;
    private final JumpNoteFactory jumpNoteFactory;
    private final CompetitionRepository competitionRepository;

    @Autowired
    public JumpNoteService(final DistanceService distanceService,
                           final JuryNotesService juryNotesService,
                           final WindCompensationService windCompensationService,
                           final GateCompensationService gateCompensationService,
                           final JumpNoteFactory jumpNoteFactory,
                           final CompetitionRepository competitionRepository) {
        this.distanceService = distanceService;
        this.juryNotesService = juryNotesService;
        this.windCompensationService = windCompensationService;
        this.gateCompensationService = gateCompensationService;
        this.jumpNoteFactory = jumpNoteFactory;
        this.competitionRepository = competitionRepository;
    }

    public JumpNote calculateOfficialJump(final JumpDTO jump) {
        final JumpNoteComposition composition = JumpNoteComposition.builder().build();
        return calculateJumpNoteWithComposition(jump, composition);
    }

    public JumpNote calculateLocalJump(final JumpDTO jump) {
        final JumpNoteComposition composition = JumpNoteComposition.builder().withAdditionalPoints(false).build();
        return calculateJumpNoteWithComposition(jump, composition);
    }

    public JumpNote calculateTrainingJump(final JumpDTO jump) {
        final JumpNoteComposition composition = JumpNoteComposition.builder().withJuryNotes(false).build();
        return calculateJumpNoteWithComposition(jump, composition);
    }

    private JumpNote calculateJumpNoteWithComposition(final JumpDTO jump, final JumpNoteComposition composition) {
        final Competition competition = competitionRepository.getOne(jump.getCompetitionId());
        NoteDecorator note = new NoteDecorator(new NoteImpl());
        note = decorateWithDistancePoints(note, jump.getDistance(), competition);

        if (composition.isWithJuryNotes()) {
            note = decorateWithJuryPoints(note, jump.getJuryNotes());
        }

        if (composition.isWithAdditionalPoints()) {
            note = decorateWithWindPoints(note, jump.getWindSpeed(), competition);
            note = decorateWithGatePoints(note, jump.getGate(), competition);
        }

        return jumpNoteFactory.create(note);
    }

    private NoteDecorator decorateWithDistancePoints(final NoteDecorator note,
                                                     final float distance,
                                                     final Competition competition) {
       final BigDecimal distancePoints = distanceService.calculate(distance, competition);
       return new DistanceNoteDecorator(note, distancePoints);
   }

    private NoteDecorator decorateWithJuryPoints(final NoteDecorator note,
                                                 final List<Float> juryNotes) {
        final BigDecimal juryNote = juryNotesService.calculate(juryNotes);
        return new JuryNoteDecorator(note, juryNote);
    }

    private NoteDecorator decorateWithWindPoints(final NoteDecorator note,
                                                 final float windSpeed,
                                                 final Competition competition) {
        final BigDecimal windCompensation = windCompensationService.calculate(windSpeed, competition);
        return new WindCompensationDecorator(note, windCompensation);
    }

    private NoteDecorator decorateWithGatePoints(final NoteDecorator note,
                                                 final int gate,
                                                 final Competition competition) {
        final BigDecimal gateCompensation = gateCompensationService.calculate(gate, competition);
        return new GateCompensationDecorator(note, gateCompensation);
    }
}
