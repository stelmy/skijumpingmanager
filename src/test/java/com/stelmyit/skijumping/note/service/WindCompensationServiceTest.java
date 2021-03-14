package com.stelmyit.skijumping.note.service;

import com.stelmyit.skijumping.competition.model.Competition;
import com.stelmyit.skijumping.hill.model.Hill;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

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

        // when
        final BigDecimal result = uut.calculate(windSpeed, competition);

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

    private static Competition competition(final int hillSize) {
        return Competition.builder()
            .id(100L)
            .baseGate(12)
            .hill(
                Hill.builder()
                    .id(100L)
                    .name("Wielka Krokiew")
                    .city("Zakopane")
                    .kpoint(hillSize - 20)
                    .hillSize(hillSize)
                    .build()
            )
            .build();
    }

}
