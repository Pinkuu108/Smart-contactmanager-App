package com.smart.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.smart.dao.UserReposatory;
import com.smart.entity.User;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private UserReposatory userReposatory;

    @ModelAttribute("user")
    public User addCommonUser(Principal principal) {

        if (principal == null) {
            return null;
        }

        String username = principal.getName();
        return userReposatory.getUserByuserName(username);
    }
}
