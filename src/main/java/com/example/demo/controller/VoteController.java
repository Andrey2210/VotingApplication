package com.example.demo.controller;

import com.example.demo.entities.VoterInfo;
import com.example.demo.entities.Voting;
import com.example.demo.services.UserService;
import com.example.demo.services.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

@Controller
public class VoteController {
    private final VotingService votingService;
    private final UserService userService;

    @Autowired
    public VoteController(VotingService votingService, UserService userService) {
        this.votingService = votingService;
        this.userService = userService;
    }

    /**
     * This method creates a new vote based on the data entered by the registered user
     * in the form in the browser
     * @param voting Vote created by user
     * @param request HttpServletRequest
     * @return message on the successful creation of voting and URL on it
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = {"/votings"}, method = RequestMethod.POST)
    @ResponseBody
    public String addVote(@RequestBody Voting voting, HttpServletRequest request) {
        Voting savedVote = votingService.save(voting);
        if (savedVote.getId() != null) {
            String url = "'" + request.getRequestURL() + "/" + savedVote.getId() + "'";
            return "{'message': 'Vote was created', 'url': " + url + "}";
        } else {
            return "{'message': 'Sorry, Vote wasn't created'}";
        }
    }

    /**
     * This method returns a Voting like Json, checks the user's voice according to ip and cookie
     * @param id Voting id
     * @param request HttpServletRequest
     * @return
     */
    @RequestMapping(value = {"/votings/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Voting> showVote(@PathVariable Long id, HttpServletRequest request) {
        Voting vote = votingService.findById(id);
        String ip = request.getRemoteAddr();
        long voteId = vote.getId();
        boolean flag = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("votingNumbers".equals(cookie.getName())) {
                    Stream<String> votingNumbersStream = Stream.of(cookie.getValue().trim().split(" "));
                    flag = votingNumbersStream.map(Long::parseLong).anyMatch(p -> p == voteId);
                }
            }
        }
        boolean flag2 = vote.getAnswers().stream()
                .map(p -> p.getVoterInfos()
                        .stream()
                        .map(VoterInfo::getIpAddress))
                .anyMatch(ip::equals);
        return flag || flag2 ? new ResponseEntity<Voting>(vote, HttpStatus.OK) :
                new ResponseEntity<Voting>(vote, HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = {"/votings/{id}"}, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<String> closeVote(@PathVariable Long id) {
        Voting voting = votingService.findById(id);
        votingService.closeVote(voting);
        return new ResponseEntity<String>("Vote was closed", HttpStatus.OK);
        }

    @RequestMapping(value = {"/votings/{id}/result"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Voting> showVoteResult(@PathVariable Long id) {
        Voting vote = votingService.findById(id);
             return new ResponseEntity<Voting>(vote, HttpStatus.OK);
    }
}
