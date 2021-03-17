package com.stelmyit.skijumping.jump.jump.dto;

import com.stelmyit.skijumping.jump.juryNote.dto.JuryNoteDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JumpDTO {
    private Long jumperId;
    private Long competitionRoundId;
    private String status;
    private float distance;
    private int gate;
    private float windSpeed;
    private List<JuryNoteDTO> juryNotes;
}
