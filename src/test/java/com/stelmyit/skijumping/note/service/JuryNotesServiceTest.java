package com.stelmyit.skijumping.note.service;

import com.stelmyit.skijumping.common.exception.IncorrectJuryNotesAmountException;
import com.stelmyit.skijumping.jump.model.Jump;
import com.stelmyit.skijumping.juryNote.model.JuryNote;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.stream.Stream.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class JuryNotesServiceTest {

    private final JuryNotesService uut = new JuryNotesService();

    @Test
    public void shouldThrowExceptionIfTooLessNotes() {
        // given
        final List<Float> juryNotes = asList(10f, 15f);
        final Jump jump = jump(juryNotes);

        // when
        try {
            uut.calculate(jump);
        } catch (IncorrectJuryNotesAmountException exception) {
            assertThat(exception.getMessage())
                .isEqualTo("Incorrect jury notes amount. There should be exactly 5 notes, but 2 found.");
        }
    }

    @Test
    public void shouldThrowExceptionIfTooManyNotes() {
        // given
        final List<Float> juryNotes = asList(10f, 15f, 15f, 15f, 15f, 15f);
        final Jump jump = jump(juryNotes);

        // when
        try {
            uut.calculate(jump);
        } catch (IncorrectJuryNotesAmountException exception) {
            assertThat(exception.getMessage())
                .isEqualTo("Incorrect jury notes amount. There should be exactly 5 notes, but 6 found.");
        }
    }

    @Test
    public void shouldThrowExceptionIfZeroNotes() {
        // given
        final List<Float> juryNotes = emptyList();
        final Jump jump = jump(juryNotes);

        // when
        try {
            uut.calculate(jump);
        } catch (IncorrectJuryNotesAmountException exception) {
            assertThat(exception.getMessage())
                .isEqualTo("Incorrect jury notes amount. There should be exactly 5 notes, but 0 found.");
        }
    }

    @Test
    public void shouldThrowExceptionIfNullNotes() {
        // given
        final Jump jump = jump(null);

        // when
        try {
            uut.calculate(jump);
        } catch (IncorrectJuryNotesAmountException exception) {
            assertThat(exception.getMessage())
                .isEqualTo("Incorrect jury notes amount. There should be exactly 5 notes, but 0 found.");
        }
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void shouldCalculateJuryNotes(List<Float> juryNotes, BigDecimal totalNote) {
        // given
        final Jump jump = jump(juryNotes);

        // when
        final BigDecimal result = uut.calculate(jump);

        // then
        assertThat(result).isEqualTo(totalNote);
    }

    private static Stream<Arguments> testData() {
        return of(
            arguments(asList(15f, 15f, 15f, 15f, 15f), BigDecimal.valueOf(45.0)),
            arguments(asList(10f, 15f, 15f, 15f, 15f), BigDecimal.valueOf(45.0)),
            arguments(asList(10f, 15f, 15f, 15f, 20f), BigDecimal.valueOf(45.0)),
            arguments(asList(10f, 10f, 10f, 10f, 10f), BigDecimal.valueOf(30.0)),
            arguments(asList(20f, 20f, 20f, 20f, 20f), BigDecimal.valueOf(60.0))
        );
    }

    private static Jump jump(final List<Float> juryNotes) {
        return Jump.builder()
            .distance(125F)
            .juryNotes(juryNotes(juryNotes))
            .gate(12)
            .windSpeed(1.5F)
            .build();
    }

    private static List<JuryNote> juryNotes(List<Float> juryNotes) {
        if (juryNotes == null) {
            return null;
        }

        return juryNotes.stream()
            .map(note -> JuryNote.builder()
                .note(note)
                .build())
            .collect(Collectors.toList());
    }
}
