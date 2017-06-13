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
import org.springframework.web.bind.annotation.*;

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


}
