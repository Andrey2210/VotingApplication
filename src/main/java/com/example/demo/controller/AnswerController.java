package com.example.demo.controller;

import com.example.demo.entities.Answer;
import com.example.demo.entities.Voting;
import com.example.demo.services.AnswerService;
import com.example.demo.services.VoterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
     * @return Voting object in JSON string
     */
    @RequestMapping(value = {"/answers/{id}"}, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Voting> addVoice(@PathVariable Long id, HttpServletRequest request) {
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
        HttpHeaders headers = addCookie(voting.getId(), request);
        return new ResponseEntity<Voting>(voting, headers, HttpStatus.OK);
    }

    private HttpHeaders addCookie(long votingId, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        HttpHeaders headers = new HttpHeaders();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if ("votingNumbers".equals(cookies[i].getName())) {
                    String newVotingNumbers = cookies[i].getValue().concat(" " + votingId);
                    headers.add("Set-Cookie","votingNumbers=" + newVotingNumbers);
                } else {
                    headers.add("Set-Cookie","votingNumbers=" + votingId);}
            }
        } else {
            headers.add("Set-Cookie","votingNumbers=" + votingId);
        }
        return  headers;
    }
}
