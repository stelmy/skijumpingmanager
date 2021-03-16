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

public class WindCompensationCalculatorTest {

    private final WindCompensationCalculator uut = new WindCompensationCalculator();

    @ParameterizedTest
    @MethodSource("testData")
    public void shouldCalculateJuryNotes(final float windSpeed, final int hillSize, final BigDecimal expectedPoints) {
        // given
        final Jump jump = Jump.builder().windSpeed(windSpeed).build();
        final Hill hill = Hill.builder().hillSize(hillSize).build();

        // when
        final BigDecimal result = uut.calculate(jump, hill);

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

}
