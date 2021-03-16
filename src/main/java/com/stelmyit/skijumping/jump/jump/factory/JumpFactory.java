package com.stelmyit.skijumping.jump.jump.factory;

import com.stelmyit.skijumping.common.factory.CommonFactory;
import com.stelmyit.skijumping.competition.competitionRound.model.CompetitionRound;
import com.stelmyit.skijumping.competition.competitionRound.repository.CompetitionRoundRepository;
import com.stelmyit.skijumping.jump.jump.dto.JumpDTO;
import com.stelmyit.skijumping.jump.jump.model.Jump;
import com.stelmyit.skijumping.jump.juryNote.dto.JuryNoteDTO;
import com.stelmyit.skijumping.jump.juryNote.factory.JuryNoteFactory;
import com.stelmyit.skijumping.jump.juryNote.model.JuryNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JumpFactory extends CommonFactory<Jump, JumpDTO> {

    private final JuryNoteFactory juryNoteFactory;
    private final CompetitionRoundRepository competitionRoundRepository;

    @Autowired
    public JumpFactory(final JuryNoteFactory juryNoteFactory,
                       final CompetitionRoundRepository competitionRoundRepository) {
        this.juryNoteFactory = juryNoteFactory;
        this.competitionRoundRepository = competitionRoundRepository;
    }

    // TODO: add unit tests
    @Override
    public Jump createEntity(JumpDTO jumpDTO) {
        final List<JuryNote> juryNotes = juryNoteFactory.createEntities(jumpDTO.getJuryNotes());
        final CompetitionRound competitionRound = competitionRoundRepository.getOne(jumpDTO.getCompetitionRoundId());
        return Jump.builder()
            .competitionRound(competitionRound)
            .distance(jumpDTO.getDistance())
            .gate(jumpDTO.getGate())
            .windSpeed(jumpDTO.getWindSpeed())
            .juryNotes(juryNotes)
            .build();
    }

    // TODO: add unit test
    @Override
    public JumpDTO createDTO(Jump jump) {
        final List<JuryNoteDTO> juryNotes = juryNoteFactory.createDtos(jump.getJuryNotes());
        return JumpDTO.builder()
            .competitionRoundId(jump.getCompetitionRound().getId())
            .distance(jump.getDistance())
            .juryNotes(juryNotes)
            .gate(jump.getGate())
            .windSpeed(jump.getWindSpeed())
            .build();
    }

}
