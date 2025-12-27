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

public class LoginSuccessTest {

    private final UserService service = Mockito.mock(UserService.class);
    private final JwtTokenProvider jwt = Mockito.mock(JwtTokenProvider.class);
    private final AuthController controller = new AuthController(service, jwt);

    @Test
    public void testLoginSuccessReturnsToken() {
        AuthRequest auth = new AuthRequest("vidhya@gmail.com", "test123");
        User user = new User(1L, "vidhya@gmail.com", "test123", "USER");

        Mockito.when(service.findByEmail("vidhya@gmail.com")).thenReturn(user);
        Mockito.when(jwt.generateToken(1L, "vidhya@gmail.com", "USER")).thenReturn("TOKEN123");

        ResponseEntity<AuthResponse> response = controller.login(auth);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody().getToken());
        assertEquals("TOKEN123", response.getBody().getToken());
    }
}