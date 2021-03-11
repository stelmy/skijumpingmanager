package com.stelmyit.skijumping.competition.calculator.service.jurynotes;

import com.stelmyit.skijumping.competition.calculator.exception.IncorrectJuryNotesAmountException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.math.BigDecimal.ZERO;
import static java.util.Optional.ofNullable;

@Service
public class JuryNotesService {
    private static final int NUMBER_OF_NOTES = 5;
    private static final BigDecimal MAX_NOTE = BigDecimal.valueOf(20);
    private static final BigDecimal MIN_NOTE = ZERO;

    public BigDecimal calculate(final List<BigDecimal> juryNotesToCalculate) {
        final List<BigDecimal> juryNotes = ofNullable(juryNotesToCalculate).orElse(new ArrayList<>());
        if (!isNotesAmountCorrect(juryNotes)) {
            throw new IncorrectJuryNotesAmountException(juryNotes.size());
        }

        final BigDecimal noteSum = getNoteSum(juryNotes);
        final BigDecimal max = getMaxNote(juryNotes);
        final BigDecimal min = getMinNote(juryNotes);
        return noteSum.subtract(min).subtract(max);
    }

    private boolean isNotesAmountCorrect(final List<BigDecimal> juryNotes) {
        return juryNotes.size() == NUMBER_OF_NOTES;
    }

    private BigDecimal getNoteSum(final List<BigDecimal> juryNotes) {
        return juryNotes.stream().reduce(ZERO, BigDecimal::add);
    }

    private BigDecimal getMaxNote(final List<BigDecimal> juryNotes) {
        return Optional.of(juryNotes.stream().max(BigDecimal::compareTo)).get().orElse(MAX_NOTE);
    }

    private BigDecimal getMinNote(final List<BigDecimal> juryNotes) {
        return Optional.of(juryNotes.stream().min(BigDecimal::compareTo)).get().orElse(MIN_NOTE);
    }

}
