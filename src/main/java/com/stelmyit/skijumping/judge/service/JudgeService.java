package com.stelmyit.skijumping.judge.service;

import com.stelmyit.skijumping.common.service.CommonService;
import com.stelmyit.skijumping.judge.dto.JudgeDTO;
import com.stelmyit.skijumping.judge.factory.JudgeFactory;
import com.stelmyit.skijumping.judge.model.Judge;
import com.stelmyit.skijumping.judge.repository.JudgeRepository;
import org.springframework.stereotype.Service;

@Service
public class JudgeService extends CommonService<Judge, JudgeDTO, JudgeRepository, JudgeFactory> {

    public JudgeService(JudgeRepository repository, JudgeFactory factory) {
        super(repository, factory);
    }
}
