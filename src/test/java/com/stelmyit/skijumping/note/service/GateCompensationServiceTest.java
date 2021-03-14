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

public class GateCompensationServiceTest {

    private final DistanceHillValueService distanceHillValueService = new DistanceHillValueService();
    private final GateCompensationService uut = new GateCompensationService(distanceHillValueService);

    @ParameterizedTest
    @MethodSource("testData")
    public void shouldCalculateJuryNotes(final int gate,
                                         final int baseGate,
                                         final int kPoint,
                                         final BigDecimal expectedPoints) {
        // given
        final Competition competition = competition(baseGate, kPoint);

        // when
        final BigDecimal result = uut.calculate(gate, competition);

        // then
        assertThat(result).isEqualTo(expectedPoints);
    }

    private static Stream<Arguments> testData() {
        return of(
            arguments(22, 20, 95, BigDecimal.valueOf(-10.0)),
            arguments(18, 20, 95, BigDecimal.valueOf(10.0)),
            arguments(20, 20, 95, BigDecimal.valueOf(0.0)),
            arguments(22, 20, 33, BigDecimal.valueOf(-20.0)),
            arguments(18, 20, 33, BigDecimal.valueOf(20.0)),
            arguments(20, 20, 33, BigDecimal.valueOf(0.0))
        );
    }

    private static Competition competition(final int baseGate, final int kPoint) {
        return Competition.builder()
            .id(100L)
            .baseGate(baseGate)
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
