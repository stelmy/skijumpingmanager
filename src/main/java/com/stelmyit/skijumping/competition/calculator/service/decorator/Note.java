package com.stelmyit.skijumping.competition.calculator.service.decorator;

import java.math.BigDecimal;

public interface Note {
    BigDecimal calculateTotalPoints();
    BigDecimal calculateDistancePoints();
    BigDecimal calculateJuryPoints();
    BigDecimal calculateAdditionPoints();
}