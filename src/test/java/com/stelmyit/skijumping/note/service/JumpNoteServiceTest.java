package com.stelmyit.skijumping.note.service;

import com.stelmyit.skijumping.city.model.City;
import com.stelmyit.skijumping.competition.model.Competition;
import com.stelmyit.skijumping.competition.repository.CompetitionRepository;
import com.stelmyit.skijumping.competitionRound.model.CompetitionRound;
import com.stelmyit.skijumping.country.model.Country;
import com.stelmyit.skijumping.hill.model.Hill;
import com.stelmyit.skijumping.jump.model.Jump;
import com.stelmyit.skijumping.jump.repository.JumpRepository;
import com.stelmyit.skijumping.juryNote.model.JuryNote;
import com.stelmyit.skijumping.note.factory.JumpNoteFactory;
import com.stelmyit.skijumping.note.model.JumpNote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JumpNoteServiceTest {

    private final DistanceService distanceService = mock(DistanceService.class);
    private final JuryNotesService juryNotesService = mock(JuryNotesService.class);
    private final WindCompensationService windCompensationService = mock(WindCompensationService.class);
    private final GateCompensationService gateCompensationService = mock(GateCompensationService.class);
    private final JumpNoteFactory jumpNoteFactory = new JumpNoteFactory();
    private final CompetitionRepository competitionRepository = mock(CompetitionRepository.class);
    private final JumpRepository jumpRepository = mock(JumpRepository.class);
    private final JumpNoteService uut = new JumpNoteService(distanceService, juryNotesService, windCompensationService, gateCompensationService, jumpNoteFactory, competitionRepository, jumpRepository);

    private static final BigDecimal DISTANCE_NOTE = BigDecimal.valueOf(100);
    private static final BigDecimal JURY_NOTE = BigDecimal.valueOf(50);
    private static final BigDecimal WIND_COMPENSATION = BigDecimal.valueOf(15);
    private static final BigDecimal GATE_COMPENSATION = BigDecimal.valueOf(15);
    private Jump jump;

    @BeforeEach
    public void setUp() {
        when(distanceService.calculate(any(), any())).thenReturn(DISTANCE_NOTE);
        when(juryNotesService.calculate(any())).thenReturn(JURY_NOTE);
        when(windCompensationService.calculate(any(), any())).thenReturn(WIND_COMPENSATION);
        when(gateCompensationService.calculate(any(), any())).thenReturn(GATE_COMPENSATION);
        when(competitionRepository.getOne(anyLong())).thenReturn(competition());
        jump = jump();
        when(jumpRepository.getOne(anyLong())).thenReturn(jump);
    }

    @Test
    public void shouldNotIgnoreJuryNotesInOfficialJump() {
        // when
        final JumpNote note = uut.calculateOfficialJump(jump.getId());

        // then
        assertThat(note.getDistanceNote()).isNotZero();
        assertThat(note.getJuryNote()).isNotZero();
        assertThat(note.getAdditionalNote()).isNotZero();
    }

    @Test
    public void shouldIgnoreJuryNotesInTrainingJump() {
        // when
        final JumpNote note = uut.calculateTrainingJump(jump.getId());

        // then
        assertThat(note.getDistanceNote()).isNotZero();
        assertThat(note.getJuryNote()).isZero();
        assertThat(note.getAdditionalNote()).isNotZero();
    }

    @Test
    public void shouldIgnoreAdditionalNotesInLocalJump() {
        // when
        final JumpNote note = uut.calculateLocalJump(jump.getId());

        // then
        assertThat(note.getDistanceNote()).isNotZero();
        assertThat(note.getJuryNote()).isNotZero();
        assertThat(note.getAdditionalNote()).isZero();
    }

    private static Jump jump() {
        return Jump.builder()
            .id(1L)
            .competitionRound(CompetitionRound.builder().id(1L).build())
            .distance(130F)
            .juryNotes(juryNotes())
            .gate(12)
            .windSpeed(1.5F)
            .build();
    }

    // TODO: uspójnienie danych testowych
    private static Competition competition() {
        return Competition.builder()
            .id(100L)
            .hill(
                Hill.builder()
                    .id(200L)
                    .name("Wielka Krokiew")
                    .city(
                        City.builder()
                            .id(1L)
                            .name("Zakopane")
                            .country(
                                Country.builder()
                                    .id(1L)
                                    .name("Polska")
                                    .build()
                            )
                            .build()
                    )
                    .hillSize(140)
                    .kpoint(125)
                    .build()
            )
            .competitionRounds(asList(
                CompetitionRound.builder()
                    .id(1L)
                    .baseGate(12)
                    .build(),
                CompetitionRound.builder()
                    .id(2L)
                    .baseGate(15)
                    .build()
            ))
            .build();
    }

    private static List<JuryNote> juryNotes() {
        return asList(
            JuryNote.builder()
                .id(1L)
                .note(18.5F)
                .build(),
            JuryNote.builder()
                .id(2L)
                .note(18.5F)
                .build(),
            JuryNote.builder()
                .id(3L)
                .note(18.5F)
                .build(),
            JuryNote.builder()
                .id(4L)
                .note(18.5F)
                .build(),
            JuryNote.builder()
                .id(5L)
                .note(18.5F)
                .build()
        );
    }

}
