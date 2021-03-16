package com.stelmyit.skijumping.jump.juryNote.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JuryNoteDTO {
    private Long judgeId;
    private float note;
}
