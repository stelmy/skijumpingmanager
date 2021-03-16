package com.stelmyit.skijumping.result.competitionResult.calculator;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static java.util.Optional.ofNullable;

@Service
public class CompetitionPositionScoreCalculator {

    private static final Map<Integer, Integer> POSITION_SCORE_MAP = new HashMap<>() {
        {
            put(1, 100);
            put(2, 80);
            put(3, 60);
            put(4, 50);
            put(5, 45);
            put(6, 40);
            put(7, 36);
            put(8, 32);
            put(9, 29);
            put(10, 26);
            put(11, 24);
            put(12, 22);
            put(13, 20);
            put(14, 18);
            put(15, 16);
            put(16, 15);
            put(17, 14);
            put(18, 13);
            put(19, 12);
            put(20, 11);
            put(21, 10);
            put(22, 9);
            put(23, 8);
            put(24, 7);
            put(25, 6);
            put(26, 5);
            put(27, 4);
            put(28, 3);
            put(29, 2);
            put(30, 1);
        }
    };

    public int calculate(final int position) {
        return ofNullable(POSITION_SCORE_MAP.get(position)).orElse(0);
    }
}
