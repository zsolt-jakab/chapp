package com.app.chat.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.chat.app.service.UserServiceImpl;

import java.security.Principal;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private UserServiceImpl underTest;
    
    @Before
    public void setUp() {
        underTest = new UserServiceImpl();
    }
    
    @Test
    public void isAuthenticatedTest_withAuthentication() {
        // GIVEN
        Principal principal = mock(Principal.class);
        
        // WHEN
        boolean result = underTest.isAuthenticated(principal);
        
        // THEN
        assertThat(result).isTrue();
    }
    
    @Test
    public void isAuthenticatedTest_withoutAuthentication() {
        // GIVEN
        Principal principal = null;
        
        // WHEN
        boolean result = underTest.isAuthenticated(principal);
        
        // THEN
        assertThat(result).isFalse();
    }
}
