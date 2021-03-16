package com.stelmyit.skijumping.score.calculator;

import com.stelmyit.skijumping.competition.competition.model.Competition;
import com.stelmyit.skijumping.competition.competition.repository.CompetitionRepository;
import com.stelmyit.skijumping.competition.competitionRound.model.CompetitionRound;
import com.stelmyit.skijumping.hill.model.Hill;
import com.stelmyit.skijumping.jump.jump.model.Jump;
import com.stelmyit.skijumping.score.calculator.decorator.*;
import com.stelmyit.skijumping.score.factory.JumpScoreFactory;
import com.stelmyit.skijumping.score.model.JumpScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.stelmyit.skijumping.competition.competitionRound.model.CompetitionRoundType.OFFICIAL;

@Service
public class JumpScoreCalculator {

    private final JumpScoreFactory jumpScoreFactory;
    private final DistanceCalculator distanceCalculator;
    private final JuryNotesCalculator juryNotesCalculator;
    private final WindCompensationCalculator windCompensationCalculator;
    private final GateCompensationCalculator gateCompensationCalculator;
    private final CompetitionRepository competitionRepository;

    @Autowired
    public JumpScoreCalculator(final JumpScoreFactory jumpScoreFactory,
                               final DistanceCalculator distanceCalculator,
                               final JuryNotesCalculator juryNotesCalculator,
                               final WindCompensationCalculator windCompensationCalculator,
                               final GateCompensationCalculator gateCompensationCalculator,
                               final CompetitionRepository competitionRepository) {
        this.jumpScoreFactory = jumpScoreFactory;
        this.distanceCalculator = distanceCalculator;
        this.juryNotesCalculator = juryNotesCalculator;
        this.windCompensationCalculator = windCompensationCalculator;
        this.gateCompensationCalculator = gateCompensationCalculator;
        this.competitionRepository = competitionRepository;
    }

    public JumpScore calculateJumpScore(Jump jump) {
        final Competition competition = competitionRepository.findByRoundId(jump.getCompetitionRound().getId());
        final Hill hill = competition.getHill();
        final CompetitionRound competitionRound = jump.getCompetitionRound();

        ScoreDecorator score = new ScoreDecorator(new ScoreImpl());
        score = decorateWithDistancePoints(score, jump, hill);
        score = decorateWithWindPoints(score, jump, hill);
        score = decorateWithGatePoints(score, jump, hill, competitionRound.getBaseGate());

        if (OFFICIAL.equals(competitionRound.getType())) {
            score = decorateWithJuryPoints(score, jump);
        }

        return jumpScoreFactory.createEntity(score, jump);
    }

    private ScoreDecorator decorateWithDistancePoints(final ScoreDecorator score,
                                                      final Jump jump,
                                                      final Hill hill) {
        final BigDecimal distancePoints = distanceCalculator.calculate(jump, hill);
        return new DistancePointsDecorator(score, distancePoints);
    }

    private ScoreDecorator decorateWithJuryPoints(final ScoreDecorator score,
                                                  final Jump jump) {
        final BigDecimal juryNote = juryNotesCalculator.calculate(jump);
        return new JuryPointsDecorator(score, juryNote);
    }

    private ScoreDecorator decorateWithWindPoints(final ScoreDecorator score,
                                                  final Jump jump,
                                                  final Hill hill) {
        final BigDecimal windCompensation = windCompensationCalculator.calculate(jump, hill);
        return new WindPointsDecorator(score, windCompensation);
    }

    private ScoreDecorator decorateWithGatePoints(final ScoreDecorator score,
                                                  final Jump jump,
                                                  final Hill hill,
                                                  final int baseGate) {
        final BigDecimal gateCompensation = gateCompensationCalculator.calculate(jump, hill, baseGate);
        return new GatePointsDecorator(score, gateCompensation);
    }

}
