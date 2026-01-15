package com.varosha.springboot.taskmanagement.Configuration;

import com.varosha.springboot.taskmanagement.Models.User;
import com.varosha.springboot.taskmanagement.Repository.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ExtractEmail {

    public String getEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {
            return auth.getName();
        }throw new RuntimeException("No authenticated user found in the security context");
    }

    public User getCurrentUser(){
        UserRepo userRepo = null;
        return userRepo.findByEmail(getEmail()).orElseThrow(() -> new RuntimeException("User not found!!!"));
    }

}