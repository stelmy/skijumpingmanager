package com.stelmyit.skijumping.competition.calculator.service.jump;

import com.stelmyit.skijumping.competition.calculator.decorator.*;
import com.stelmyit.skijumping.competition.calculator.dto.Jump;
import com.stelmyit.skijumping.competition.calculator.factory.JumpNoteFactory;
import com.stelmyit.skijumping.competition.calculator.model.JumpNote;
import com.stelmyit.skijumping.competition.calculator.service.distance.DistanceService;
import com.stelmyit.skijumping.competition.calculator.service.gate.GateCompensationService;
import com.stelmyit.skijumping.competition.calculator.service.jurynotes.JuryNotesService;
import com.stelmyit.skijumping.competition.calculator.service.wind.WindCompensationService;
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

    @Autowired
    public JumpNoteService(final DistanceService distanceService,
                           final JuryNotesService juryNotesService,
                           final WindCompensationService windCompensationService,
                           final GateCompensationService gateCompensationService,
                           final JumpNoteFactory jumpNoteFactory) {
        this.distanceService = distanceService;
        this.juryNotesService = juryNotesService;
        this.windCompensationService = windCompensationService;
        this.gateCompensationService = gateCompensationService;
        this.jumpNoteFactory = jumpNoteFactory;
    }

    public JumpNote calculateOfficialJump(final Jump jump) {
        final JumpNoteComposition composition = JumpNoteComposition.builder().build();
        return calculateJumpNoteWithComposition(jump, composition);
    }

    public JumpNote calculateLocalJump(final Jump jump) {
        final JumpNoteComposition composition = JumpNoteComposition.builder().withAdditionalPoints(false).build();
        return calculateJumpNoteWithComposition(jump, composition);
    }

    public JumpNote calculateTrainingJump(final Jump jump) {
        final JumpNoteComposition composition = JumpNoteComposition.builder().withJuryNotes(false).build();
        return calculateJumpNoteWithComposition(jump, composition);
    }

    private JumpNote calculateJumpNoteWithComposition(final Jump jump, final JumpNoteComposition composition) {
        NoteDecorator note = new NoteDecorator(new NoteImpl());
        note = decorateWithDistancePoints(note, jump.getDistance(), jump.getKpoint());

        if (composition.isWithJuryNotes()) {
            note = decorateWithJuryPoints(note, jump.getJuryNotes());
        }

        if (composition.isWithAdditionalPoints()) {
            note = decorateWithWindPoints(note, jump.getWindSpeed(), jump.getHillSize());
            note = decorateWithGatePoints(note, jump.getGate(), jump.getBaseGate(), jump.getKpoint());
        }

        return jumpNoteFactory.create(note);
    }

    private NoteDecorator decorateWithDistancePoints(final NoteDecorator note, final float distance, final int kPoint) {
       final BigDecimal distancePoints = distanceService.calculate(distance, kPoint);
       return new DistanceNoteDecorator(note, distancePoints);
   }

    private NoteDecorator decorateWithJuryPoints(final NoteDecorator note, final List<Float> juryNotes) {
        final BigDecimal juryNote = juryNotesService.calculate(juryNotes);
        return new JuryNoteDecorator(note, juryNote);
    }

    private NoteDecorator decorateWithWindPoints(final NoteDecorator note, final float windSpeed, final int hillSize) {
        final BigDecimal windCompensation = windCompensationService.calculate(windSpeed, hillSize);
        return new WindCompensationDecorator(note, windCompensation);
    }

    private NoteDecorator decorateWithGatePoints(final NoteDecorator note, final int gate, final int baseGate, final int kPoint) {
        final BigDecimal gateCompensation = gateCompensationService.calculate(gate, baseGate, kPoint);
        return new GateCompensationDecorator(note, gateCompensation);
    }
}
