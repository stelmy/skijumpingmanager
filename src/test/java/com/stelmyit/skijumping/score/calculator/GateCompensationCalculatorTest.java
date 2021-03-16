package com.stelmyit.skijumping.score.calculator;

import com.stelmyit.skijumping.hill.model.Hill;
import com.stelmyit.skijumping.jump.jump.model.Jump;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static java.util.stream.Stream.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class GateCompensationCalculatorTest {

    private final DistanceHillValueCalculator distanceHillValueCalculator = new DistanceHillValueCalculator();
    private final GateCompensationCalculator uut = new GateCompensationCalculator(distanceHillValueCalculator);

    @ParameterizedTest
    @MethodSource("testData")
    public void shouldCalculateJuryNotes(final int gate,
                                         final int baseGate,
                                         final int kPoint,
                                         final BigDecimal expectedPoints) {
        // given
        final Hill hill = Hill.builder().kpoint(kPoint).build();
        final Jump jump = Jump.builder().gate(gate).build();

        // when
        final BigDecimal result = uut.calculate(jump, hill, baseGate);

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
