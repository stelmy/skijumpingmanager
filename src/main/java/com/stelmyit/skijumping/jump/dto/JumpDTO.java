package com.stelmyit.skijumping.jump.dto;

import com.stelmyit.skijumping.juryNote.model.dto.JuryNoteDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JumpDTO {
    private Long competitionRoundId;
    private float distance;
    private int gate;
    private float windSpeed;
    private List<JuryNoteDTO> juryNotes;
}
