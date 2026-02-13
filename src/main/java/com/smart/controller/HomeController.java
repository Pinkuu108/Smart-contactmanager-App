package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserReposatory;
import com.smart.entity.User;
import com.smart.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private UserReposatory userReposatory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ================= HOME =================
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Home | Smart Contact Manager");
        return "home";
    }

    // ================= ABOUT =================
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About | Smart Contact Manager");
        return "about";
    }

    // ================= SIGNUP PAGE =================
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title", "Signup | Smart Contact Manager");
        model.addAttribute("user", new User()); // IMPORTANT
        return "signup";
    }

    // ================= REGISTER USER =================
    @PostMapping("/do_register")
    public String register(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            @RequestParam(value = "agreement", defaultValue = "false") boolean agreement,
            Model model,
            HttpSession session) {

        // Terms & conditions check
        if (!agreement) {
            model.addAttribute("user", user);
            model.addAttribute("message",
                    new Message("Please accept Terms & Conditions", "alert-danger"));
            return "signup";
        }

        // Validation errors
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "signup";
        }

        // Save user
        user.setRole("ROLE_USER");
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userReposatory.save(user);

        // Success message
        session.setAttribute("message",
                new Message("Registration successful! Please login.", "alert-success"));

        return "redirect:/login";
    }

    // ================= LOGIN PAGE =================
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login | Smart Contact Manager");
        return "login";
    }
}
