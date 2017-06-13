package com.example.demo.controller;


import com.example.demo.entities.Voting;
import com.example.demo.services.UserService;
import com.example.demo.services.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

@Controller
public class UserController {
    private final VotingService votingService;
    private final UserService userService;

    @Autowired
    public UserController(VotingService votingService, UserService userService) {
        this.votingService = votingService;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = {"/users/{id}/votings"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Set<Voting>> getVotes(@PathVariable long id) {
        Set<Voting> votes= userService.findById(id).getVotingSet();
        return new ResponseEntity<Set<Voting>>(votes, HttpStatus.OK);
    }

}