package com.stelmyit.skijumping.note.service;

import com.stelmyit.skijumping.competition.model.Competition;
import com.stelmyit.skijumping.competition.repository.CompetitionRepository;
import com.stelmyit.skijumping.hill.model.Hill;
import com.stelmyit.skijumping.jump.dto.JumpDTO;
import com.stelmyit.skijumping.note.factory.JumpNoteFactory;
import com.stelmyit.skijumping.note.model.JumpNote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JumpNoteServiceTest {

    private final DistanceService distanceService = mock(DistanceService.class);
    private final JuryNotesService juryNotesService = mock(JuryNotesService.class);
    private final WindCompensationService windCompensationService = mock(WindCompensationService.class);
    private final GateCompensationService gateCompensationService = mock(GateCompensationService.class);
    private final JumpNoteFactory jumpNoteFactory = new JumpNoteFactory();
    private final CompetitionRepository competitionRepository = mock(CompetitionRepository.class);
    private final JumpNoteService uut = new JumpNoteService(distanceService, juryNotesService, windCompensationService, gateCompensationService, jumpNoteFactory, competitionRepository);

    private static final BigDecimal DISTANCE_NOTE = BigDecimal.valueOf(100);
    private static final BigDecimal JURY_NOTE = BigDecimal.valueOf(50);
    private static final BigDecimal WIND_COMPENSATION = BigDecimal.valueOf(15);
    private static final BigDecimal GATE_COMPENSATION = BigDecimal.valueOf(15);

    @BeforeEach
    public void setUp() {
        when(distanceService.calculate(anyFloat(), any(Competition.class))).thenReturn(DISTANCE_NOTE);
        when(juryNotesService.calculate(anyList())).thenReturn(JURY_NOTE);
        when(windCompensationService.calculate(anyFloat(), any(Competition.class))).thenReturn(WIND_COMPENSATION);
        when(gateCompensationService.calculate(anyInt(), any(Competition.class))).thenReturn(GATE_COMPENSATION);
        when(competitionRepository.getOne(anyLong())).thenReturn(competition());
    }

    @Test
    public void shouldNotIgnoreJuryNotesInOfficialJump() {
        // given
        final JumpDTO jump = createJump();

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
        final JumpDTO jump = createJump();

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
        final JumpDTO jump = createJump();

        // when
        final JumpNote note = uut.calculateLocalJump(jump);

        // then
        assertThat(note.getDistanceNote()).isNotZero();
        assertThat(note.getJuryNote()).isNotZero();
        assertThat(note.getAdditionalNote()).isZero();
    }

    private static JumpDTO createJump() {
        return JumpDTO.builder()
            .competitionId(1L)
            .distance(125.5f)
            .juryNotes(asList(18.5f, 18f, 19.5f, 19f, 19f))
            .gate(14)
            .windSpeed(-1.8f)
            .build();
    }

    private static Competition competition() {
        return Competition.builder()
            .id(100L)
            .baseGate(12)
            .hill(
                Hill.builder()
                    .id(100L)
                    .name("Wielka Krokiew")
                    .city("Zakopane")
                    .kpoint(125)
                    .hillSize(140)
                    .build()
            )
            .build();
    }

}
