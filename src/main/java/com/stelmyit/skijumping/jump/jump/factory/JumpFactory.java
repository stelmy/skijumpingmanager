package com.stelmyit.skijumping.jump.jump.factory;

import com.stelmyit.skijumping.common.factory.CommonFactory;
import com.stelmyit.skijumping.competition.competitionRound.model.CompetitionRound;
import com.stelmyit.skijumping.competition.competitionRound.repository.CompetitionRoundRepository;
import com.stelmyit.skijumping.jump.jump.dto.JumpDTO;
import com.stelmyit.skijumping.jump.jump.model.Jump;
import com.stelmyit.skijumping.jump.juryNote.dto.JuryNoteDTO;
import com.stelmyit.skijumping.jump.juryNote.factory.JuryNoteFactory;
import com.stelmyit.skijumping.jump.juryNote.model.JuryNote;
import com.stelmyit.skijumping.jumper.model.Jumper;
import com.stelmyit.skijumping.jumper.repository.JumperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.stelmyit.skijumping.jump.jump.model.JumpStatus.VALID;

@Service
public class JumpFactory extends CommonFactory<Jump, JumpDTO> {

    private final JuryNoteFactory juryNoteFactory;
    private final CompetitionRoundRepository competitionRoundRepository;
    private final JumperRepository jumperRepository;

    @Autowired
    public JumpFactory(final JuryNoteFactory juryNoteFactory,
                       final CompetitionRoundRepository competitionRoundRepository,
                       final JumperRepository jumperRepository) {
        this.juryNoteFactory = juryNoteFactory;
        this.competitionRoundRepository = competitionRoundRepository;
        this.jumperRepository = jumperRepository;
    }

    // TODO: add unit tests
    @Override
    public Jump createEntity(JumpDTO jumpDTO) {
        final List<JuryNote> juryNotes = juryNoteFactory.createEntities(jumpDTO.getJuryNotes());
        final CompetitionRound competitionRound = competitionRoundRepository.getOne(jumpDTO.getCompetitionRoundId());
        final Jumper jumper = jumperRepository.getOne(jumpDTO.getJumperId());
        return Jump.builder()
            .jumper(jumper)
            .status(VALID)
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
            .jumperId(jump.getJumper().getId())
            .status(jump.getStatus().name())
            .competitionRoundId(jump.getCompetitionRound().getId())
            .distance(jump.getDistance())
            .juryNotes(juryNotes)
            .gate(jump.getGate())
            .windSpeed(jump.getWindSpeed())
            .build();
    }

}
