package com.app.chat.controller;

import com.chat.app.controller.UserController;
import com.chat.app.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    public void getUserTest_withAuthentication() throws Exception {
        // GIVEN
        Principal principal = () -> "chapp";

        // WHEN
        ResultActions result = mockMvc.perform(get("/user")
                .principal(principal));

        // THEN
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name").value("chapp"));
    }

    @Test
    public void getUserTest_withoutAuthentication() throws Exception {
        // WHEN
        ResultActions result = mockMvc.perform(get("/user"));

        // THEN
        result.andExpect(status().isOk())
                .andExpect(content().string(EMPTY));
    }

    @Test
    public void isAuthenticatedTest_withoutAuthentication() throws Exception {
        // GIVEN
        Principal nullPrincipal = null;

        // WHEN
        ResultActions result = mockMvc.perform(get("/isAuthenticated"));

        // THEN
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("false"));

        verify(userService).isAuthenticated(nullPrincipal);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void isAuthenticatedTest_withAuthentication() throws Exception {
        // GIVEN
        Principal principal = mock(Principal.class);
        when(userService.isAuthenticated(principal)).thenReturn(true);

        // WHEN
        ResultActions result = mockMvc.perform(get("/isAuthenticated")
                .principal(principal));

        // THEN
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("true"));

        verify(userService).isAuthenticated(principal);
        verifyNoMoreInteractions(userService);
    }
}
