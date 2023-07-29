package me.fani.michael.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public String getAuthenticatedUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
