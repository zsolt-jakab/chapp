package com.chat.app.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.app.service.UserService;

@RestController
public class UserController {
  
  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/user")
  public Principal getUser(Principal principal) {
    return principal;
  }
  
  @GetMapping("/isAuthenticated")
  public boolean isAuthenticated(Principal principal) {
    return userService.isAuthenticated(principal);
  }
}
