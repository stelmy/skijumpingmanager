package com.stelmyit.skijumping.result.competitionResult.factory;

import com.stelmyit.skijumping.common.factory.CommonFactory;
import com.stelmyit.skijumping.result.competitionResult.dto.CompetitionResultDTO;
import com.stelmyit.skijumping.result.competitionResult.model.CompetitionResult;
import org.springframework.stereotype.Service;

@Service
public class CompetitionResultFactory extends CommonFactory<CompetitionResult, CompetitionResultDTO> {

    // TODO: implement
    @Override
    public CompetitionResult createEntity(CompetitionResultDTO competitionResultDTO) {
        return null;
    }

    // TODO: implement
    @Override
    public CompetitionResultDTO createDTO(CompetitionResult entity) {
        return null;
    }
}
