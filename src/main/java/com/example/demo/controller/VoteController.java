package com.example.demo.controller;

import com.example.demo.entities.Answer;
import com.example.demo.entities.VoterInfo;
import com.example.demo.entities.Voting;
import com.example.demo.services.AnswerService;
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
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Stream;

@Controller
public class VoteController {
    private final VotingService votingService;
    private final AnswerService answerService;
    private final UserService userService;

    @Autowired
    public VoteController(VotingService votingService, AnswerService answerService, UserService userService) {
        this.votingService = votingService;
        this.answerService = answerService;
        this.userService = userService;
    }

    @RequestMapping(value = {"/votings"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Voting>> getVotes() {
        List<Voting> votes = votingService.findAll();
        return new ResponseEntity<List<Voting>>(votes, HttpStatus.OK);
    }

    /**
     * This method creates a new vote based on the data entered by the registered user
     * in the form in the browser
     *
     * @param voting  Vote created by user
     * @param request HttpServletRequest
     * @return message on the successful creation of voting and URL on it
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = {"/votings"}, method = RequestMethod.POST)
    @ResponseBody
    public String addVote(@RequestBody Voting voting, HttpServletRequest request) {
        Voting savedVote = votingService.save(voting);
        if (savedVote.getId() != null) {
            String url = "http://localhost:8080/home#!/votings/" + savedVote.getId();
            return "{\"message\": \"Vote was created\", \"url\": \"" + url + "\"}";
        } else {
            return "{\"message\": \"Sorry, Vote wasn't created\"}";
        }
    }

    /**
     * This method returns a Voting like Json, checks the user's voice according to ip and cookie
     *
     * @param id      Voting id
     * @param request HttpServletRequest
     * @return
     */
    @RequestMapping(value = {"/votings/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Voting> showVote(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response,
                                           @CookieValue(value = "votingNumbers", defaultValue = "0") String votingNumbers) {
        Voting vote = votingService.findById(id);
        if (vote.getState().toString().equals("Locked")) {
            return new ResponseEntity<Voting>(vote, HttpStatus.NO_CONTENT);
        }
        String ip = request.getRemoteAddr();
        long voteId = vote.getId();
        Stream<String> votingNumbersStream = Stream.of(votingNumbers.trim().split("_"));
        boolean flag = votingNumbersStream.map(Long::parseLong).anyMatch(p -> p == voteId);
        boolean flag2 = vote.getAnswers().stream()
                .flatMap(p -> p.getVoterInfos().stream())
                .map(VoterInfo::getIpAddress).anyMatch(ip::equals);
        return (!flag && !flag2) ? new ResponseEntity<Voting>(vote, HttpStatus.OK) :
                new ResponseEntity<Voting>(vote, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    @RequestMapping(value = {"/votings/{id}"}, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Voting> takeVoice(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response,
                                            @RequestParam(value = "answerId", required = false) Long answerId,
                                            @CookieValue(value = "votingNumbers", defaultValue = "0") String votingNumbers) {
        Voting voting = votingService.findById(id);
        if (answerId != null) {
            addVoice(answerId, votingNumbers, request, response);
        }
        return new ResponseEntity<Voting>(voting, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = {"/votings/{id}"}, method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> closeVote(@PathVariable Long id) {
        Voting voting = votingService.findById(id);
        votingService.closeVote(voting);
        return new ResponseEntity<String>("Vote was closed", HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = {"/votings/{id}/result"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Voting> showVoteResult(@PathVariable Long id) {
        Voting vote = votingService.findById(id);
        return new ResponseEntity<Voting>(vote, HttpStatus.OK);
    }

    private void addVoice(Long id, String votingNumbers, HttpServletRequest request, HttpServletResponse response) {
        Answer answer = answerService.findById(id);
        String ip = request.getRemoteAddr();
        if (ip != null) {
            answerService.addVoice(answer, ip);
        } else {
            int amount = answer.getVotersNumber() + 1;
            answer.setVotersNumber(amount);
            answerService.save(answer);
        }
        Voting voting = answer.getVoting();
        addCookie(voting.getId(), response, votingNumbers);
    }

    private void addCookie(long votingId, HttpServletResponse response, String votingNumbers) {
        String newVotingNumbers = votingNumbers.concat("_" + votingId);
        Cookie cookie = new Cookie("votingNumbers", newVotingNumbers);
        cookie.setMaxAge(3600 * 24 * 30);
        response.addCookie(cookie);
    }
}
