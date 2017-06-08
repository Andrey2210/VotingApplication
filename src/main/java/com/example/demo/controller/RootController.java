package com.example.demo.controller;

import com.example.demo.entities.User;
import com.example.demo.entities.Voting;
import com.example.demo.services.UserService;
import com.example.demo.services.VotingService;
import com.example.demo.services.security.SecurityService;
import com.example.demo.services.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class RootController {
    private final UserService userService;
    private final SecurityService securityService;
    private final UserValidator userValidator;
    private final VotingService votingService;

    @Autowired
    public RootController(UserService userService, SecurityService securityService, UserValidator userValidator,
                          VotingService votingService) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
        this.votingService = votingService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);
        securityService.autoLogin(userForm.getEmail(), userForm.getPassword());

        return "redirect:/votings";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error) {
        if (error != null)
            model.addAttribute("error", "Your username or password is invalid.");
        return "login";
    }

    @RequestMapping(value = {"/", "/votings"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        List<Voting> votings = votingService.findAllActiveVotes();
        if(votings != null && !votings.isEmpty()) {
            model.addAttribute("Votings", votings);
        } else {
            model.addAttribute("message", "No active votes");
        }
        return "votings";
    }
}
