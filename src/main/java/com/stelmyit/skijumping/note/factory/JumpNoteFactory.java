package com.stelmyit.skijumping.note.factory;

import com.stelmyit.skijumping.note.decorator.Note;
import com.stelmyit.skijumping.note.model.JumpNote;
import org.springframework.stereotype.Service;

@Service
public class JumpNoteFactory {

    public JumpNote create(final Note note) {
        return JumpNote.builder()
            .distanceNote(note.calculateDistancePoints())
            .juryNote(note.calculateJuryPoints())
            .additionalNote(note.calculateAdditionalPoints())
            .totalNote(note.calculateTotalPoints())
            .build();
    }
}
