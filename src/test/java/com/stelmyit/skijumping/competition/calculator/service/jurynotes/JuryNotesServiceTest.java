package com.stelmyit.skijumping.competition.calculator.service.jurynotes;

import com.stelmyit.skijumping.competition.calculator.exception.IncorrectJuryNotesAmountException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.stream.Stream.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class JuryNotesServiceTest {

    private static final BigDecimal _10 = BigDecimal.valueOf(10);
    private static final BigDecimal _15 = BigDecimal.valueOf(15);
    private static final BigDecimal _20 = BigDecimal.valueOf(20);

    private final JuryNotesService uut = new JuryNotesService();

    @Test
    public void shouldThrowExceptionIfTooLessNotes() {
        // given
        final List<BigDecimal> juryNotes = asList(_10, _15);

        // when
        try {
            uut.calculate(juryNotes);
        } catch (IncorrectJuryNotesAmountException exception) {
            assertThat(exception.getMessage())
                .isEqualTo("Incorrect jury notes amount. There should be exactly 5 notes, but 2 found.");
        }
    }

    @Test
    public void shouldThrowExceptionIfTooManyNotes() {
        // given
        final List<BigDecimal> juryNotes = asList(_10, _15, _15, _15, _15, _15);

        // when
        try {
            uut.calculate(juryNotes);
        } catch (IncorrectJuryNotesAmountException exception) {
            assertThat(exception.getMessage())
                .isEqualTo("Incorrect jury notes amount. There should be exactly 5 notes, but 6 found.");
        }
    }

    @Test
    public void shouldThrowExceptionIfZeroNotes() {
        // given
        final List<BigDecimal> juryNotes = emptyList();

        // when
        try {
            uut.calculate(juryNotes);
        } catch (IncorrectJuryNotesAmountException exception) {
            assertThat(exception.getMessage())
                .isEqualTo("Incorrect jury notes amount. There should be exactly 5 notes, but 0 found.");
        }
    }

    @Test
    public void shouldThrowExceptionIfNullNotes() {
        // given
        final List<BigDecimal> juryNotes = null;

        // when
        try {
            uut.calculate(juryNotes);
        } catch (IncorrectJuryNotesAmountException exception) {
            assertThat(exception.getMessage())
                .isEqualTo("Incorrect jury notes amount. There should be exactly 5 notes, but 0 found.");
        }
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void shouldCalculateJuryNotes(List<BigDecimal> juryNotes, BigDecimal totalNote) {
        // when
        final BigDecimal result = uut.calculate(juryNotes);

        // then
        assertThat(result).isEqualTo(totalNote);
    }

    private static Stream<Arguments> testData() {
        return of(
            arguments(asList(_15, _15, _15, _15, _15), BigDecimal.valueOf(45)),
            arguments(asList(_10, _15, _15, _15, _15), BigDecimal.valueOf(45)),
            arguments(asList(_10, _15, _15, _15, _20), BigDecimal.valueOf(45)),
            arguments(asList(_10, _10, _10, _10, _10), BigDecimal.valueOf(30)),
            arguments(asList(_20, _20, _20, _20, _20), BigDecimal.valueOf(60))
        );
    }
}
