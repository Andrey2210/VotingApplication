package com.example.demo.controller;

import com.example.demo.entities.Answer;
import com.example.demo.entities.Voting;
import com.example.demo.services.AnswerService;
import com.example.demo.services.VoterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AnswerController {
    private final AnswerService answerService;
    private final VoterInfoService voterInfoService;

    @Autowired
    public AnswerController(AnswerService answerService, VoterInfoService voterInfoService) {
        this.answerService = answerService;
        this.voterInfoService = voterInfoService;
    }

    /**
     * This method adds a voice to the selected answer and saves information about the voted user
     *
     * @param id       Answer id
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return Voting object in JSON string
     */
    @RequestMapping(value = {"/answers/{id}"}, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Voting> addVoice(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
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
        addCookie(voting.getId(), request, response);
        return new ResponseEntity<Voting>(voting, HttpStatus.OK);
    }

    private void addCookie(long votingId, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        Cookie votingNumbers = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if ("votingNumbers".equals(cookies[i].getName())) {
                    String newVotingNumbers = cookies[i].getValue().concat(" " + votingId);
                    votingNumbers = new Cookie("votingNumbers", newVotingNumbers);
                } else {
                    votingNumbers = new Cookie("votingNumbers", "" + votingId);
                }
            }
        } else {
            votingNumbers = new Cookie("votingNumbers", "" + votingId);
        }
        votingNumbers.setMaxAge(0);
        response.addCookie(votingNumbers);
    }
}
