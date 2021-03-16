package com.stelmyit.skijumping.judge.service;

import com.stelmyit.skijumping.judge.dto.JudgeDTO;
import com.stelmyit.skijumping.judge.factory.JudgeFactory;
import com.stelmyit.skijumping.judge.model.Judge;
import com.stelmyit.skijumping.judge.repository.JudgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JudgeService {

    private final JudgeRepository judgeRepository;
    private final JudgeFactory judgeFactory;

    @Autowired
    public JudgeService(final JudgeRepository judgeRepository,
                        final JudgeFactory judgeFactory) {
        this.judgeRepository = judgeRepository;
        this.judgeFactory = judgeFactory;
    }

    public List<JudgeDTO> getAll() {
        final List<Judge> allJudges = judgeRepository.findAll();
        return judgeFactory.createDtos(allJudges);
    }
}
