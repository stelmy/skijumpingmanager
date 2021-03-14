package com.stelmyit.skijumping.note.service;

import com.stelmyit.skijumping.common.exception.IncorrectJuryNotesAmountException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.math.RoundingMode.DOWN;
import static java.util.Optional.ofNullable;

@Service
public class JuryNotesService {
    private static final int NUMBER_OF_NOTES = 5;
    private static final float MAX_NOTE = 20f;
    private static final float MIN_NOTE = 0f;

    public BigDecimal calculate(final List<Float> juryNotesToCalculate) {
        final List<Float> juryNotes = ofNullable(juryNotesToCalculate).orElse(new ArrayList<>());
        if (!isNotesAmountCorrect(juryNotes)) {
            throw new IncorrectJuryNotesAmountException(juryNotes.size());
        }

        final float noteSum = getNoteSum(juryNotes);
        final float max = getMaxNote(juryNotes);
        final float min = getMinNote(juryNotes);
        return BigDecimal.valueOf(noteSum - max - min).setScale(1, DOWN);
    }

    private boolean isNotesAmountCorrect(final List<Float> juryNotes) {
        return juryNotes.size() == NUMBER_OF_NOTES;
    }

    private float getNoteSum(final List<Float> juryNotes) {
        return juryNotes.stream().reduce(0f, Float::sum);
    }

    private float getMaxNote(final List<Float> juryNotes) {
        return Optional.of(juryNotes.stream().max(Float::compareTo)).get().orElse(MAX_NOTE);
    }

    private float getMinNote(final List<Float> juryNotes) {
        return Optional.of(juryNotes.stream().min(Float::compareTo)).get().orElse(MIN_NOTE);
    }

}
