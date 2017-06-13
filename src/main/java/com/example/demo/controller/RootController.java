package com.example.demo.controller;

import com.example.demo.entities.User;
import com.example.demo.entities.Voting;
import com.example.demo.services.UserService;
import com.example.demo.services.VotingService;
import com.example.demo.services.security.SecurityService;
import com.example.demo.services.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
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
    public String registrationPage(Model model) {
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
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error) {
        if (error != null)
            model.addAttribute("error", "Your username or password is invalid.");
        return "login";
    }

    @GetMapping(value = {"/logout"})
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/home";
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String showAllVotingsPage(Model model) {
        User user = userService.findByEmail(getPrincipal());
        if(user != null) {
            model.addAttribute("User", user);
        }
        return "index";
    }
    private String getPrincipal() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
