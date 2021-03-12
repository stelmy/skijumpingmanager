package com.stelmyit.skijumping.competition.calculator.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Jump {
    private int kpoint;
    private int hillSize;
    private int baseGate;
    private float distance;
    private int gate;
    private float windSpeed;
    private List<Float> juryNotes;
}
