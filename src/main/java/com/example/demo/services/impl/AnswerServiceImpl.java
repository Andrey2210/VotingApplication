package com.example.demo.services.impl;


import com.example.demo.entities.Answer;
import com.example.demo.entities.VoterInfo;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.services.AnswerService;
import com.example.demo.services.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

@Service
public class AnswerServiceImpl implements AnswerService<Answer> {
    private final AnswerRepository answerRepository;
    private final VotingService votingService;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository, VotingService votingService) {
        this.answerRepository = answerRepository;
        this.votingService = votingService;
    }

    @Override
    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public Answer findById(long id) {
        return answerRepository.findOne(id);
    }

    @Override
    public void addVoice(Answer answer, String ip) {
        VoterInfo voterInfo = new VoterInfo();
        voterInfo.setIpAddress(ip);
        voterInfo.setVotedDate(new Date());
        voterInfo.setAnswer(answer);
        Set<VoterInfo> voterInfos = answer.getVoterInfos();
        voterInfos.add(voterInfo);
        int amount = answer.getVotersNumber() + 1;
        answer.setVotersNumber(amount);
        answerRepository.saveAndFlush(answer);
    }
}

