package com.stelmyit.skijumping.note.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class JumpNote {
    private BigDecimal distanceNote;
    private BigDecimal juryNote;
    private BigDecimal additionalNote;
    private BigDecimal totalNote;
}
