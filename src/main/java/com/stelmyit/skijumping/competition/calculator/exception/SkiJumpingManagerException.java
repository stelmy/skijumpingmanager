package com.stelmyit.skijumping.competition.calculator.exception;

public abstract class SkiJumpingManagerException extends RuntimeException {

    protected SkiJumpingManagerException(final String errorMessage) {
        super(errorMessage);
    }

}
