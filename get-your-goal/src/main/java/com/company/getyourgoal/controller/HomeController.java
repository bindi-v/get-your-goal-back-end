package com.company.getyourgoal.controller;

import com.company.getyourgoal.model.User;
import com.company.getyourgoal.repository.CommentRepository;
import com.company.getyourgoal.repository.GoalRepository;
import com.company.getyourgoal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

//@CrossOrigin("http://localhost:3000")
@RestController
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GoalRepository goalRepository;

    @Autowired
    CommentRepository commentRepository;


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public String home() {
        return "home";
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        if (request.getSession(false) != null) {
            return "redirect:/";
        }
        model.addAttribute("user", new User());
        return "login";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/logout")
    public String logout() {
        return "logout";
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
