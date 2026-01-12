package com.varosha.springboot.taskmanagement.Configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserDetailsExtractions {

    static Authentication authentication = SecurityContextHolder
            .getContext()
            .getAuthentication();

    private static String email = authentication.getPrincipal().toString();

    public static String getUserEmail() {
        if (authentication != null && authentication.isAuthenticated()) {
            return email;
        }
        return null;
    }


}
