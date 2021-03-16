package com.stelmyit.skijumping.score.calculator;

import com.stelmyit.skijumping.competition.competition.model.Competition;
import com.stelmyit.skijumping.competition.competition.repository.CompetitionRepository;
import com.stelmyit.skijumping.competition.competitionRound.model.CompetitionRound;
import com.stelmyit.skijumping.jump.jump.model.Jump;
import com.stelmyit.skijumping.score.factory.JumpScoreFactory;
import com.stelmyit.skijumping.score.model.JumpScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.stelmyit.skijumping.competition.competitionRound.model.CompetitionRoundType.OFFICIAL;
import static com.stelmyit.skijumping.competition.competitionRound.model.CompetitionRoundType.TRAINING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JumpNoteCalculatorTest {

    private final DistanceCalculator distanceCalculator = mock(DistanceCalculator.class);
    private final JuryNotesCalculator juryNotesCalculator = mock(JuryNotesCalculator.class);
    private final WindCompensationCalculator windCompensationCalculator = mock(WindCompensationCalculator.class);
    private final GateCompensationCalculator gateCompensationCalculator = mock(GateCompensationCalculator.class);
    private final JumpScoreFactory jumpScoreFactory = new JumpScoreFactory();
    private final CompetitionRepository competitionRepository = mock(CompetitionRepository.class);
    private final JumpScoreCalculator uut = new JumpScoreCalculator(jumpScoreFactory, distanceCalculator,
        juryNotesCalculator, windCompensationCalculator, gateCompensationCalculator, competitionRepository);

    private static final BigDecimal DISTANCE_NOTE = BigDecimal.valueOf(100);
    private static final BigDecimal JURY_NOTE = BigDecimal.valueOf(50);
    private static final BigDecimal WIND_COMPENSATION = BigDecimal.valueOf(15);
    private static final BigDecimal GATE_COMPENSATION = BigDecimal.valueOf(15);

    @BeforeEach
    public void setUp() {
        when(distanceCalculator.calculate(any(), any())).thenReturn(DISTANCE_NOTE);
        when(juryNotesCalculator.calculate(any())).thenReturn(JURY_NOTE);
        when(windCompensationCalculator.calculate(any(), any())).thenReturn(WIND_COMPENSATION);
        when(gateCompensationCalculator.calculate(any(), any(), anyInt())).thenReturn(GATE_COMPENSATION);
        when(competitionRepository.findByRoundId(any())).thenReturn(Competition.builder().build());
    }

    @Test
    public void shouldNotIgnoreJuryNotesInOfficialJump() {
        // given
        final Jump jump = Jump.builder()
            .competitionRound(CompetitionRound.builder().type(OFFICIAL).build())
            .build();

        // when
        final JumpScore score = uut.calculateJumpScore(jump);

        // then
        assertThat(score.getDistanceScore()).isNotZero();
        assertThat(score.getJuryScore()).isNotZero();
        assertThat(score.getWindCompensation()).isNotZero();
        assertThat(score.getGateCompensation()).isNotZero();
        assertThat(score.getTotalScore()).isNotZero();
    }

    @Test
    public void shouldIgnoreJuryNotesInTrainingJump() {
        // given
        final Jump jump = Jump.builder()
            .competitionRound(CompetitionRound.builder().type(TRAINING).build())
            .build();

        // when
        final JumpScore score = uut.calculateJumpScore(jump);

        // then
        assertThat(score.getDistanceScore()).isNotZero();
        assertThat(score.getJuryScore()).isZero();
        assertThat(score.getWindCompensation()).isNotZero();
        assertThat(score.getGateCompensation()).isNotZero();
        assertThat(score.getTotalScore()).isNotZero();
    }

}
