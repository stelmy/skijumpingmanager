package com.stelmyit.skijumping.note.service;

import com.stelmyit.skijumping.city.model.City;
import com.stelmyit.skijumping.competition.model.Competition;
import com.stelmyit.skijumping.competitionRound.model.CompetitionRound;
import com.stelmyit.skijumping.country.model.Country;
import com.stelmyit.skijumping.hill.model.Hill;
import com.stelmyit.skijumping.jump.model.Jump;
import com.stelmyit.skijumping.juryNote.model.JuryNote;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Stream.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class DistanceServiceTest {

    private final DistanceHillValueService distanceHillValueService = new DistanceHillValueService();
    private final DistanceService uut = new DistanceService(distanceHillValueService);

    @ParameterizedTest
    @MethodSource("testData")
    public void shouldCalculateDistanceValue(final float distance, final int kPoint, final BigDecimal expectedValue) {
        // given
        final Jump jump = jump(distance);
        final Competition competition = competition(kPoint);

        // when
        final BigDecimal result = uut.calculate(jump, competition);

        // then
        assertThat(result).isEqualTo(expectedValue);
    }

    private static Stream<Arguments> testData() {
        return of(
            arguments(120, 120, BigDecimal.valueOf(60.0)),
            arguments(130, 120, BigDecimal.valueOf(78.0)),
            arguments(110, 120, BigDecimal.valueOf(42.0)),
            arguments(95, 95, BigDecimal.valueOf(60.0)),
            arguments(100, 95, BigDecimal.valueOf(70.0)),
            arguments(90, 95, BigDecimal.valueOf(50.0)),
            arguments(200, 200, BigDecimal.valueOf(120.0)),
            arguments(190, 200, BigDecimal.valueOf(108.0)),
            arguments(210, 200, BigDecimal.valueOf(132.0))
        );
    }

    private static Jump jump(final float distance) {
        return Jump.builder()
            .distance(distance)
            .juryNotes(juryNotes())
            .gate(12)
            .windSpeed(1.5F)
            .build();
    }

    // TODO: uspójnienie danych testowych
    private static Competition competition(final int kPoint) {
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
                    .kpoint(kPoint)
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
