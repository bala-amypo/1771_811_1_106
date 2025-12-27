package com.example.demo.auth;

import com.example.demo.controller.AuthController;
import com.example.demo.dto.AuthRequest;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class LoginWrongPasswordTest {

    private final UserService service = Mockito.mock(UserService.class);
    private final JwtTokenProvider jwt = Mockito.mock(JwtTokenProvider.class);
    private final AuthController controller = new AuthController(service, jwt);

    @Test
    public void testLoginWrongPasswordUnauthorized() {
        AuthRequest req = new AuthRequest("vidhya@gmail.com", "wrong");
        User stored = new User(1L, "vidhya@gmail.com", "correct", "USER");

        Mockito.when(service.findByEmail("vidhya@gmail.com")).thenReturn(stored);

        ResponseEntity<?> response = controller.login(req);

        assertEquals(401, response.getStatusCodeValue());
        assertEquals("Invalid email or password", response.getBody());
    }
}