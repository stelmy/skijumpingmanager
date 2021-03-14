package com.stelmyit.skijumping.note.decorator;

import java.math.BigDecimal;

public interface Note {
    BigDecimal calculateTotalPoints();
    BigDecimal calculateDistancePoints();
    BigDecimal calculateJuryPoints();
    BigDecimal calculateAdditionalPoints();
}
