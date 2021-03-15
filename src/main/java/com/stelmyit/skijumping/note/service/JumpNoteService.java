package com.stelmyit.skijumping.note.service;

import com.stelmyit.skijumping.competition.model.Competition;
import com.stelmyit.skijumping.competition.repository.CompetitionRepository;
import com.stelmyit.skijumping.jump.model.Jump;
import com.stelmyit.skijumping.jump.repository.JumpRepository;
import com.stelmyit.skijumping.note.decorator.*;
import com.stelmyit.skijumping.note.factory.JumpNoteFactory;
import com.stelmyit.skijumping.note.model.JumpNote;
import com.stelmyit.skijumping.note.model.JumpNoteComposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class JumpNoteService {

    private final DistanceService distanceService;
    private final JuryNotesService juryNotesService;
    private final WindCompensationService windCompensationService;
    private final GateCompensationService gateCompensationService;
    private final JumpNoteFactory jumpNoteFactory;
    private final CompetitionRepository competitionRepository;
    private final JumpRepository jumpRepository;

    @Autowired
    public JumpNoteService(final DistanceService distanceService,
                           final JuryNotesService juryNotesService,
                           final WindCompensationService windCompensationService,
                           final GateCompensationService gateCompensationService,
                           final JumpNoteFactory jumpNoteFactory,
                           final CompetitionRepository competitionRepository,
                           final JumpRepository jumpRepository) {
        this.distanceService = distanceService;
        this.juryNotesService = juryNotesService;
        this.windCompensationService = windCompensationService;
        this.gateCompensationService = gateCompensationService;
        this.jumpNoteFactory = jumpNoteFactory;
        this.competitionRepository = competitionRepository;
        this.jumpRepository = jumpRepository;
    }

    public JumpNote calculateOfficialJump(final Long jumpId) {
        final JumpNoteComposition composition = JumpNoteComposition.builder().build();
        return calculateJumpNoteWithComposition(jumpId, composition);
    }

    public JumpNote calculateLocalJump(final Long jumpId) {
        final JumpNoteComposition composition = JumpNoteComposition.builder().withAdditionalPoints(false).build();
        return calculateJumpNoteWithComposition(jumpId, composition);
    }

    public JumpNote calculateTrainingJump(final Long jumpId) {
        final JumpNoteComposition composition = JumpNoteComposition.builder().withJuryNotes(false).build();
        return calculateJumpNoteWithComposition(jumpId, composition);
    }

    private JumpNote calculateJumpNoteWithComposition(final Long jumpId, final JumpNoteComposition composition) {
        final Jump jump = jumpRepository.getOne(jumpId);
        final Long competitionRoundId = jump.getCompetitionRound().getId();
        final Competition competition = competitionRepository.findByRoundId(competitionRoundId);
        NoteDecorator note = new NoteDecorator(new NoteImpl());
        note = decorateWithDistancePoints(note, jump, competition);

        if (composition.isWithJuryNotes()) {
            note = decorateWithJuryPoints(note, jump);
        }

        if (composition.isWithAdditionalPoints()) {
            note = decorateWithWindPoints(note, jump, competition);
            note = decorateWithGatePoints(note, jump, competition);
        }

        return jumpNoteFactory.create(note);
    }

    private NoteDecorator decorateWithDistancePoints(final NoteDecorator note,
                                                     final Jump jump,
                                                     final Competition competition) {
       final BigDecimal distancePoints = distanceService.calculate(jump, competition);
       return new DistanceNoteDecorator(note, distancePoints);
   }

    private NoteDecorator decorateWithJuryPoints(final NoteDecorator note,
                                                 final Jump jump) {
        final BigDecimal juryNote = juryNotesService.calculate(jump);
        return new JuryNoteDecorator(note, juryNote);
    }

    private NoteDecorator decorateWithWindPoints(final NoteDecorator note,
                                                 final Jump jump,
                                                 final Competition competition) {
        final BigDecimal windCompensation = windCompensationService.calculate(jump, competition);
        return new WindCompensationDecorator(note, windCompensation);
    }

    private NoteDecorator decorateWithGatePoints(final NoteDecorator note,
                                                 final Jump jump,
                                                 final Competition competition) {
        final BigDecimal gateCompensation = gateCompensationService.calculate(jump, competition);
        return new GateCompensationDecorator(note, gateCompensation);
    }
}
