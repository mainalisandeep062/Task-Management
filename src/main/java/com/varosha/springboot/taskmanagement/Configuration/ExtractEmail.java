package com.varosha.springboot.taskmanagement.Configuration;

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

}