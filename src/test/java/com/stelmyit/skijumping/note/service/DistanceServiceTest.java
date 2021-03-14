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

public class DistanceServiceTest {

    private final DistanceHillValueService distanceHillValueService = new DistanceHillValueService();
    private final DistanceService uut = new DistanceService(distanceHillValueService);

    @ParameterizedTest
    @MethodSource("testData")
    public void shouldCalculateDistanceValue(final float distance, final int kPoint, final BigDecimal expectedValue) {
        // given
        final Competition competition = competition(kPoint);

        // when
        final BigDecimal result = uut.calculate(distance, competition);

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

    private static Competition competition(final int kPoint) {
        return Competition.builder()
            .id(100L)
            .baseGate(12)
            .hill(
                Hill.builder()
                    .id(100L)
                    .name("Wielka Krokiew")
                    .city("Zakopane")
                    .kpoint(kPoint)
                    .hillSize(kPoint + 20)
                    .build()
            )
            .build();
    }
}
