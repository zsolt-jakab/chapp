package com.app.chat.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.chat.app.controller.UserController;
import com.chat.app.service.UserService;

import java.security.Principal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;
    
    private MockMvc mockMvc;
    
    @Before
    public void setUp() {
        UserController underTest = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }
    
    @Test
    public void isAuthenticatedTest_withoutAuthentication() throws Exception {
      //GIVEN
        Principal principal = null;
        when(userService.isAuthenticated(principal)).thenReturn(false);
        
        // WHEN
        ResultActions result = mockMvc.perform(get("/isAuthenticated"));
                
        //THEN
        result.andExpect(status().isOk())
              .andExpect(content().string("false"));
    }
    
    @Test
    public void isAuthenticatedTest_withAuthentication() throws Exception {
        //GIVEN
        Principal principal = mock(Principal.class);
        when(userService.isAuthenticated(principal)).thenReturn(true);
        
        // WHEN
        ResultActions result = mockMvc.perform(get("/isAuthenticated")
                                      .principal(principal));
        
        //THEN
        result.andExpect(status().isOk())
              .andExpect(content().string("true"));
    }
}
