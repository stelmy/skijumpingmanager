package com.stelmyit.skijumping.jump;

import com.stelmyit.skijumping.competitionRound.model.CompetitionRound;
import com.stelmyit.skijumping.competitionRound.repository.CompetitionRoundRepository;
import com.stelmyit.skijumping.judge.model.Judge;
import com.stelmyit.skijumping.judge.repository.JudgeRepository;
import com.stelmyit.skijumping.jump.dto.JumpDTO;
import com.stelmyit.skijumping.jump.model.Jump;
import com.stelmyit.skijumping.jump.repository.JumpRepository;
import com.stelmyit.skijumping.juryNote.model.JuryNote;
import com.stelmyit.skijumping.juryNote.model.dto.JuryNoteDTO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class JumpService {

    private JumpRepository jumpRepository;
    private JudgeRepository judgeRepository;
    private CompetitionRoundRepository competitionRoundRepository;

    @Autowired
    public JumpService(final JumpRepository jumpRepository,
                       final JudgeRepository judgeRepository,
                       final CompetitionRoundRepository competitionRoundRepository) {
        this.jumpRepository = jumpRepository;
        this.judgeRepository = judgeRepository;
        this.competitionRoundRepository = competitionRoundRepository;
    }

    public Long createJump(JumpDTO jumpDTO) {
        final CompetitionRound competitionRound = competitionRoundRepository.getOne(jumpDTO.getCompetitionRoundId());
        final Jump jump = Jump.builder()
            .competitionRound(competitionRound)
            .distance(jumpDTO.getDistance())
            .gate(jumpDTO.getGate())
            .windSpeed(jumpDTO.getWindSpeed())
            .juryNotes(createJuryNotes(jumpDTO))
            .build();
        final Jump savedJump = jumpRepository.save(jump);
        return savedJump.getId();
    }

    private List<JuryNote> createJuryNotes(JumpDTO jumpDTO) {
        final List<Judge> judges = getJudges(jumpDTO);
        return jumpDTO.getJuryNotes().stream()
            .map(juryNoteDTO -> JuryNote.builder()
                .note(juryNoteDTO.getNote())
                .judge(getJudgeById(judges, juryNoteDTO.getJudgeId()))
                .build())
            .collect(Collectors.toList());
    }

    private List<Judge> getJudges(JumpDTO jumpDTO) {
        final List<Long> judgeIds = jumpDTO.getJuryNotes().stream()
            .map(JuryNoteDTO::getJudgeId)
            .collect(Collectors.toList());
        return judgeRepository.findAllById(judgeIds);
    }

    private Judge getJudgeById(List<Judge> judges, Long judgeId) {
        return judges.stream()
            .filter(judge -> Objects.equals(judge.getId(), judgeId))
            .findFirst().orElse(null);
    }

}
