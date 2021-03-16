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

public class DistanceCalculatorTest {

    private final DistanceHillValueCalculator distanceHillValueCalculator = new DistanceHillValueCalculator();
    private final DistanceCalculator uut = new DistanceCalculator(distanceHillValueCalculator);

    @ParameterizedTest
    @MethodSource("testData")
    public void shouldCalculateDistanceValue(final float distance, final int kPoint, final BigDecimal expectedValue) {
        // given
        final Jump jump = Jump.builder().distance(distance).build();
        final Hill hill = Hill.builder().kpoint(kPoint).build();

        // when
        final BigDecimal result = uut.calculate(jump, hill);

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

}
