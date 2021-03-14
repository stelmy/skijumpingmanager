package com.stelmyit.skijumping.note.service;

import org.springframework.stereotype.Service;

@Service
public class DistanceHillValueService {

    public float getMeterValue(final int kPoint) {
        if (kPoint < 25) {
            return 4.8f;
        } else if (kPoint < 30) {
            return 4.4f;
        } else if (kPoint < 35) {
            return 4f;
        } else if (kPoint < 40) {
            return 3.6f;
        } else if (kPoint < 50) {
            return 3.2f;
        } else if (kPoint < 60) {
            return 2.8f;
        } else if (kPoint < 70) {
            return 2.4f;
        } else if (kPoint < 80) {
            return 2.2f;
        } else if (kPoint < 100) {
            return 2f;
        } else if (kPoint < 170) {
            return 1.8f;
        } else  {
            return 1.2f;
        }
    }

    public int getBaseDistancePoints(final int kPoint) {
        return kPoint > 170 ? 120 : 60;
    }
}

