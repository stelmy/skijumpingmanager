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

public class WindCompensationServiceTest {

    private final WindCompensationService uut = new WindCompensationService();

    @ParameterizedTest
    @MethodSource("testData")
    public void shouldCalculateJuryNotes(final float windSpeed, final int hillSize, final BigDecimal expectedPoints) {
        // given
        final Competition competition = competition(hillSize);
        final Jump jump = jump(windSpeed);

        // when
        final BigDecimal result = uut.calculate(jump, competition);

        // then
        assertThat(result).isEqualTo(expectedPoints);
    }

    private static Stream<Arguments> testData() {
        return of(
            arguments(0f, 136, BigDecimal.valueOf(0.0)),
            arguments(2f, 136, BigDecimal.valueOf(10.0)),
            arguments(-2f, 136, BigDecimal.valueOf(-10.0)),
            arguments(0f, 136, BigDecimal.valueOf(0.0)),
            arguments(2f, 136, BigDecimal.valueOf(10.0)),
            arguments(-2f, 136, BigDecimal.valueOf(-10.0))
        );
    }

    private static Jump jump(final float windSpeed) {
        return Jump.builder()
            .distance(125F)
            .juryNotes(juryNotes())
            .gate(12)
            .windSpeed(windSpeed)
            .build();
    }

    // TODO: uspójnienie danych testowych
    private static Competition competition(final int hillSize) {
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
                    .hillSize(hillSize)
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
