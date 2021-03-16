package com.stelmyit.skijumping.score.calculator.decorator;

import java.math.BigDecimal;

public interface Score {
    BigDecimal calculateTotalPoints();
    BigDecimal calculateDistancePoints();
    BigDecimal calculateJuryPoints();
    BigDecimal calculateGatePoints();
    BigDecimal calculateWindPoints();
}
