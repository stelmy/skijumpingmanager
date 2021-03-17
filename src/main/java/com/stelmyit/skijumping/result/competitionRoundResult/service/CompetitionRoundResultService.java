package com.stelmyit.skijumping.result.competitionRoundResult.service;

import com.stelmyit.skijumping.result.competitionRoundResult.dto.CompetitionRoundResultDTO;
import com.stelmyit.skijumping.result.competitionRoundResult.factory.CompetitionRoundResultFactory;
import com.stelmyit.skijumping.result.competitionRoundResult.model.CompetitionRoundResult;
import com.stelmyit.skijumping.result.competitionRoundResult.repository.CompetitionRoundResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionRoundResultService {

    private final CompetitionRoundResultFactory competitionRoundResultFactory;
    private final CompetitionRoundResultRepository competitionRoundResultRepository;

    public CompetitionRoundResultService(final CompetitionRoundResultFactory competitionRoundResultFactory,
                                         final CompetitionRoundResultRepository competitionRoundResultRepository) {
        this.competitionRoundResultFactory = competitionRoundResultFactory;
        this.competitionRoundResultRepository = competitionRoundResultRepository;
    }

    public void completeRound(Long competitionRoundId) {
        final List<CompetitionRoundResult> roundResults = competitionRoundResultFactory.createResults(competitionRoundId);
        competitionRoundResultRepository.saveAll(roundResults);
    }

    public List<CompetitionRoundResultDTO> getAllByRoundId(Long competitionRoundId) {
        final List<CompetitionRoundResult> roundResults = competitionRoundResultRepository.getAllByRoundId(competitionRoundId);
        return competitionRoundResultFactory.createDtos(roundResults);
    }
}
