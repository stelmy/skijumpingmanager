package com.stelmyit.skijumping.competition.calculator.service.jump;

import com.stelmyit.skijumping.competition.calculator.dto.Jump;
import com.stelmyit.skijumping.competition.calculator.factory.JumpNoteFactory;
import com.stelmyit.skijumping.competition.calculator.model.JumpNote;
import com.stelmyit.skijumping.competition.calculator.service.distance.DistanceHillValueService;
import com.stelmyit.skijumping.competition.calculator.service.distance.DistanceService;
import com.stelmyit.skijumping.competition.calculator.service.gate.GateCompensationService;
import com.stelmyit.skijumping.competition.calculator.service.jurynotes.JuryNotesService;
import com.stelmyit.skijumping.competition.calculator.service.wind.WindCompensationService;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class JumpNoteServiceTest {

    private final DistanceHillValueService distanceHillValueService = new DistanceHillValueService();
    private final DistanceService distanceService = new DistanceService(distanceHillValueService);
    private final JuryNotesService juryNotesService = new JuryNotesService();
    private final WindCompensationService windCompensationService = new WindCompensationService();
    private final GateCompensationService gateCompensationService = new GateCompensationService(distanceHillValueService);
    private final JumpNoteFactory jumpNoteFactory = new JumpNoteFactory();
    private final JumpNoteService uut = new JumpNoteService(distanceService, juryNotesService, windCompensationService, gateCompensationService, jumpNoteFactory);

    @Test
    public void shouldNotIgnoreJuryNotesInOfficialJump() {
        // given
        final Jump jump = createJump();

        // when
        final JumpNote note = uut.calculateOfficialJump(jump);

        // then
        assertThat(note.getDistanceNote()).isNotZero();
        assertThat(note.getJuryNote()).isNotZero();
        assertThat(note.getAdditionalNote()).isNotZero();
    }

    @Test
    public void shouldIgnoreJuryNotesInTrainingJump() {
        // given
        final Jump jump = createJump();

        // when
        final JumpNote note = uut.calculateTrainingJump(jump);

        // then
        assertThat(note.getDistanceNote()).isNotZero();
        assertThat(note.getJuryNote()).isZero();
        assertThat(note.getAdditionalNote()).isNotZero();
    }

    @Test
    public void shouldIgnoreAdditionalNotesInLocalJump() {
        // given
        final Jump jump = createJump();

        // when
        final JumpNote note = uut.calculateLocalJump(jump);

        // then
        assertThat(note.getDistanceNote()).isNotZero();
        assertThat(note.getJuryNote()).isNotZero();
        assertThat(note.getAdditionalNote()).isZero();
    }

    private static Jump createJump() {
        return Jump.builder()
            .distance(125.5f)
            .juryNotes(asList(18.5f, 18f, 19.5f, 19f, 19f))
            .baseGate(12)
            .gate(14)
            .windSpeed(-1.8f)
            .hillSize(136)
            .kpoint(120)
            .build();
    }
}
