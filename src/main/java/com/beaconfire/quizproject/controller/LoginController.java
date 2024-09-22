package com.beaconfire.quizproject.controller;

import com.beaconfire.quizproject.dao.UserDao;
import com.beaconfire.quizproject.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {


    @Autowired
    private UserDao userDAO;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        userDAO.registerUser(user);
        model.addAttribute("message", "User registered successfully!");
        return "login";
    }
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            HttpSession session,
                            Model model) {
        User user = userDAO.validateUser(email, password);
        if (user!= null) {
            session.setAttribute("userId", user.getUser_id());
            session.setAttribute("userEmail", user.getEmail());
            return "start"; // Redirect to a welcome page after successful
            // login
        } else {
            model.addAttribute("error", "Invalid email or password. Please try again.");
            return "login"; // Redirect back to login page with error
            // message
        }
    }
}