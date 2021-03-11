package com.stelmyit.skijumping.competition.calculator.exception;

public enum SkiJumpingErrorCode {
    INCORRECT_JURY_NOTES_AMOUNT("Incorrect jury notes amount. There should be exactly 5 notes, but %s found.");

    private String message;

    SkiJumpingErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
