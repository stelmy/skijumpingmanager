package com.stelmyit.skijumping.note.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.util.stream.Stream.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class DistanceHillValueServiceTest {

    private final DistanceHillValueService uut = new DistanceHillValueService();

    @ParameterizedTest
    @MethodSource("meterValueTestData")
    public void shouldCalculateMeterValue(final int kPoint, final float expectedValue) {
        // when
        final float result = uut.getMeterValue(kPoint);

        // then
        assertThat(result).isEqualTo(expectedValue);
    }

    @Test
    public void shouldCalculateBaseDistancePointForSkiFlyingHill() {
        // given
        final int kPoint = 185;

        // when
        final int result = uut.getBaseDistancePoints(kPoint);

        // then
        assertThat(result).isEqualTo(120);
    }

    @Test
    public void shouldCalculateBaseDistancePointForLargeHill() {
        // given
        final int kPoint = 125;

        // when
        final int result = uut.getBaseDistancePoints(kPoint);

        // then
        assertThat(result).isEqualTo(60);
    }

    private static Stream<Arguments> meterValueTestData() {
        return of(
            arguments(24, 4.8f),
            arguments(25, 4.4f),
            arguments(29, 4.4f),
            arguments(30, 4f),
            arguments(34, 4f),
            arguments(35, 3.6f),
            arguments(39, 3.6f),
            arguments(40, 3.2f),
            arguments(49, 3.2f),
            arguments(50, 2.8f),
            arguments(59, 2.8f),
            arguments(60, 2.4f),
            arguments(69, 2.4f),
            arguments(70, 2.2f),
            arguments(79, 2.2f),
            arguments(80, 2f),
            arguments(99, 2f),
            arguments(100, 1.8f),
            arguments(169, 1.8f),
            arguments(170, 1.2f)
        );
    }


}
