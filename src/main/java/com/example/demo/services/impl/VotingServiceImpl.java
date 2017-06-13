package com.example.demo.services.impl;

import com.example.demo.entities.User;
import com.example.demo.entities.Voting;
import com.example.demo.entities.enums.State;
import com.example.demo.repository.VotingRepository;
import com.example.demo.services.UserService;
import com.example.demo.services.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotingServiceImpl implements VotingService<Voting> {
    private final VotingRepository votingRepository;
    private final UserService userService;

    @Autowired
    public VotingServiceImpl(VotingRepository votingRepository, UserService userService) {
        this.votingRepository = votingRepository;
        this.userService = userService;
    }

    @Override
    public Voting save(Voting voting) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(auth.getName());
        voting.setUser(user);
        voting.setState(State.ACTIVE);
        voting.getAnswers().forEach(answer -> {answer.setVoting(voting);});
        return votingRepository.save(voting);
    }

    @Override
    public List<Voting> findAllActiveVotes() {
        return votingRepository.findByState(State.ACTIVE);
    }

    @Override
    public Voting findById(long id){
       return votingRepository.findOne(id);
    }

    @Override
    public List<Voting> findAll() {
        return  votingRepository.findAll();
    }

    @Override
    public void closeVote(Voting voting) {
        voting.setState(State.LOCKED);
        votingRepository.saveAndFlush(voting);
    }
}