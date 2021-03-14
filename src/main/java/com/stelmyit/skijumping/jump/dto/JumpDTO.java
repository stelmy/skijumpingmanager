package com.stelmyit.skijumping.jump.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JumpDTO {
    private Long competitionId;
    private float distance;
    private int gate;
    private float windSpeed;
    private List<Float> juryNotes;
}
