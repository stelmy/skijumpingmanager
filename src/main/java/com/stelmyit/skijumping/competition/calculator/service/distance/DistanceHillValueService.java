package com.stelmyit.skijumping.competition.calculator.service.distance;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DistanceHillValueService {

    public BigDecimal getMeterValue(final int kPoint) {
        if (kPoint < 25) {
            return BigDecimal.valueOf(4.8);
        } else if (kPoint < 30) {
            return BigDecimal.valueOf(4.4);
        } else if (kPoint < 35) {
            return BigDecimal.valueOf(4);
        } else if (kPoint < 40) {
            return BigDecimal.valueOf(3.6);
        } else if (kPoint < 50) {
            return BigDecimal.valueOf(3.2);
        } else if (kPoint < 60) {
            return BigDecimal.valueOf(2.8);
        } else if (kPoint < 70) {
            return BigDecimal.valueOf(2.4);
        } else if (kPoint < 80) {
            return BigDecimal.valueOf(2.2);
        } else if (kPoint < 100) {
            return BigDecimal.valueOf(2);
        } else if (kPoint < 170) {
            return BigDecimal.valueOf(1.8);
        } else  {
            return BigDecimal.valueOf(1.2);
        }
    }

    public BigDecimal getBaseDistancePoints(final int kPoint) {
        return kPoint > 170 ? BigDecimal.valueOf(120) : BigDecimal.valueOf(60);
    }
}

