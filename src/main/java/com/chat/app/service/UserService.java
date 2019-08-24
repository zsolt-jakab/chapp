package com.chat.app.service;

import java.security.Principal;

public interface UserService {

    boolean isAuthenticated(Principal principal);
}
