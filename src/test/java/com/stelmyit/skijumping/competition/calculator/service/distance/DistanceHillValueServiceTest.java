package com.stelmyit.skijumping.competition.calculator.service.distance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static java.util.stream.Stream.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class DistanceHillValueServiceTest {

    private final DistanceHillValueService uut = new DistanceHillValueService();

    @ParameterizedTest
    @MethodSource("meterValueTestData")
    public void shouldCalculateMeterValue(final int kPoint, final BigDecimal expectedValue) {
        // when
        final BigDecimal result = uut.getMeterValue(kPoint);

        // then
        assertThat(result).isEqualTo(expectedValue);
    }

    @Test
    public void shouldCalculateBaseDistancePointForSkiFlyingHill() {
        // given
        final int kPoint = 185;

        // when
        final BigDecimal result = uut.getBaseDistancePoints(kPoint);

        // then
        assertThat(result).isEqualTo(BigDecimal.valueOf(120));
    }

    @Test
    public void shouldCalculateBaseDistancePointForLargeHill() {
        // given
        final int kPoint = 125;

        // when
        final BigDecimal result = uut.getBaseDistancePoints(kPoint);

        // then
        assertThat(result).isEqualTo(BigDecimal.valueOf(60));
    }

    private static Stream<Arguments> meterValueTestData() {
        return of(
            arguments(24, BigDecimal.valueOf(4.8)),
            arguments(25, BigDecimal.valueOf(4.4)),
            arguments(29, BigDecimal.valueOf(4.4)),
            arguments(30, BigDecimal.valueOf(4)),
            arguments(34, BigDecimal.valueOf(4)),
            arguments(35, BigDecimal.valueOf(3.6)),
            arguments(39, BigDecimal.valueOf(3.6)),
            arguments(40, BigDecimal.valueOf(3.2)),
            arguments(49, BigDecimal.valueOf(3.2)),
            arguments(50, BigDecimal.valueOf(2.8)),
            arguments(59, BigDecimal.valueOf(2.8)),
            arguments(60, BigDecimal.valueOf(2.4)),
            arguments(69, BigDecimal.valueOf(2.4)),
            arguments(70, BigDecimal.valueOf(2.2)),
            arguments(79, BigDecimal.valueOf(2.2)),
            arguments(80, BigDecimal.valueOf(2)),
            arguments(99, BigDecimal.valueOf(2)),
            arguments(100, BigDecimal.valueOf(1.8)),
            arguments(169, BigDecimal.valueOf(1.8)),
            arguments(170, BigDecimal.valueOf(1.2))
        );
    }


}
