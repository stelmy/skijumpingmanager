package com.stelmyit.skijumping.judge.service;

import com.stelmyit.skijumping.judge.dto.JudgeDTO;
import com.stelmyit.skijumping.judge.model.Judge;
import com.stelmyit.skijumping.judge.repository.JudgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class JudgeService {

    private final JudgeRepository judgeRepository;

    @Autowired
    public JudgeService(final JudgeRepository judgeRepository) {
        this.judgeRepository = judgeRepository;
    }

    public List<JudgeDTO> getAll() {
        final List<Judge> allJudges = judgeRepository.findAll();
        return allJudges.stream()
            .map(judge -> JudgeDTO.builder()
                .firstName(judge.getFirstName())
                .lastName(judge.getLastName())
                .build())
            .collect(toList());
    }
}
