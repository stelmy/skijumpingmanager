package com.stelmyit.skijumping.common.exception;

public abstract class SkiJumpingManagerException extends RuntimeException {

    protected SkiJumpingManagerException(final String errorMessage) {
        super(errorMessage);
    }

}
