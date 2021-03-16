package com.stelmyit.skijumping.jump.juryNote.factory;

import com.stelmyit.skijumping.common.factory.CommonFactory;
import com.stelmyit.skijumping.judge.model.Judge;
import com.stelmyit.skijumping.judge.repository.JudgeRepository;
import com.stelmyit.skijumping.jump.juryNote.dto.JuryNoteDTO;
import com.stelmyit.skijumping.jump.juryNote.model.JuryNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Service
public class JuryNoteFactory extends CommonFactory<JuryNote, JuryNoteDTO> {

    private final JudgeRepository judgeRepository;

    @Autowired
    public JuryNoteFactory(final JudgeRepository judgeRepository) {
        this.judgeRepository = judgeRepository;
    }

    // TODO: implement
    @Override
    public JuryNote createEntity(JuryNoteDTO juryNoteDTO) {
        return null;
    }

    // TODO: add unit tests
    @Override
    public JuryNoteDTO createDTO(JuryNote juryNote) {
        return JuryNoteDTO.builder()
            .judgeId(juryNote.getJudge().getId())
            .note(juryNote.getNote())
            .build();
    }

    // TODO: add unit tests
    @Override
    public List<JuryNote> createEntities(List<JuryNoteDTO> juryNoteDTOS) {
        final List<Long> judgeIds = juryNoteDTOS.stream().map(JuryNoteDTO::getJudgeId).collect(toList());
        final List<Judge> judges = judgeRepository.findAllById(judgeIds);

        return juryNoteDTOS.stream()
            .map(juryNoteDTO -> {
                final Judge judge = judges.stream()
                    .filter(j -> Objects.equals(j.getId(), juryNoteDTO.getJudgeId()))
                    .findFirst().orElse(Judge.builder().build());
                return JuryNote.builder().judge(judge).note(juryNoteDTO.getNote()).build();
            })
            .collect(toList());
    }

}
