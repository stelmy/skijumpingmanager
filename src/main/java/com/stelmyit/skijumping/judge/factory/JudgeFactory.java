package com.stelmyit.skijumping.judge.factory;

import com.stelmyit.skijumping.common.factory.CommonFactory;
import com.stelmyit.skijumping.judge.dto.JudgeDTO;
import com.stelmyit.skijumping.judge.model.Judge;
import org.springframework.stereotype.Service;

@Service
public class JudgeFactory extends CommonFactory<Judge, JudgeDTO> {

    // TODO: implement
    @Override
    public Judge createEntity(JudgeDTO judgeDTO) {
        return null;
    }

    @Override
    public JudgeDTO createDTO(Judge judge) {
        return JudgeDTO.builder()
            .firstName(judge.getFirstName())
            .lastName(judge.getLastName())
            .build();
    }

}
