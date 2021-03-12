package com.stelmyit.skijumping.competition.calculator.service.jump;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JumpNoteComposition {

    @Builder.Default
    private boolean withJuryNotes = true;

    @Builder.Default
    private boolean withAdditionalPoints = true;
}
