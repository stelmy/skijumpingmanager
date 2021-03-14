package com.stelmyit.skijumping.common.exception;

import static com.stelmyit.skijumping.common.exception.SkiJumpingErrorCode.INCORRECT_JURY_NOTES_AMOUNT;

public class IncorrectJuryNotesAmountException extends SkiJumpingManagerException {

    public IncorrectJuryNotesAmountException(final int juryNotesAmount) {
        super(getErrorMessage(juryNotesAmount));
    }

    private static String getErrorMessage(final int juryNotesAmount) {
        return String.format(INCORRECT_JURY_NOTES_AMOUNT.getMessage(), juryNotesAmount);
    }
}
