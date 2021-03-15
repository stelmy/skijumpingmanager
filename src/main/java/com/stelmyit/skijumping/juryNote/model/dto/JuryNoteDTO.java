package com.stelmyit.skijumping.juryNote.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JuryNoteDTO {
    private Long judgeId;
    private float note;
}
