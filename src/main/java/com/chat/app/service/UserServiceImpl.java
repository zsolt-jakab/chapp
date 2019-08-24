package com.chat.app.service;

import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public boolean isAuthenticated(Principal principal) {
        return principal != null;
    }
}
