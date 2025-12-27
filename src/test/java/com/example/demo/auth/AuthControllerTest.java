package com.example.demo.auth;

import com.example.demo.controller.AuthController;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class AuthControllerTest {

    private final UserService userService = Mockito.mock(UserService.class);
    private final JwtTokenProvider jwtTokenProvider = Mockito.mock(JwtTokenProvider.class);

    private final AuthController authController =
            new AuthController(userService, jwtTokenProvider);

    @Test
    public void testRegisterUserSuccess() {
        User input = new User(null, "vidhya@gmail.com", "test123", "USER");
        User saved = new User(1L, "vidhya@gmail.com", "test123", "USER");

        Mockito.when(userService.register(input)).thenReturn(saved);

        ResponseEntity<User> response = authController.register(input);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("vidhya@gmail.com", response.getBody().getEmail());
    }
}