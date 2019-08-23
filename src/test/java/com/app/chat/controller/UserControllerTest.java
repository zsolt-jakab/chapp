package com.app.chat.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.chat.app.controller.UserController;
import com.chat.app.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;
    
    private UserController underTest;
    
    private MockMvc mockMvc;
    
    @Before
    public void setUp() {
        underTest = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }
}
