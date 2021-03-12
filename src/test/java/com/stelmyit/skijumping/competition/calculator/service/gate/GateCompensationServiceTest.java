package com.stelmyit.skijumping.competition.calculator.service.gate;

import com.stelmyit.skijumping.competition.calculator.service.distance.DistanceHillValueService;
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
    public void shouldCalculateJuryNotes(final int gate, final int baseGate, final int kPoint, final BigDecimal expectedPoints) {
        // when
        final BigDecimal result = uut.calculate(gate, baseGate, kPoint);

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

}
