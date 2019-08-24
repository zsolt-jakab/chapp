package com.chat.app.service;

import java.security.Principal;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public boolean isAuthenticated(Principal principal) {
        return principal != null;
    }
}
