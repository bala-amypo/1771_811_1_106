package com.example.demo.auth;

import com.example.demo.security.JwtTokenProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtValidateTokenSuccessTest {

    private final JwtTokenProvider jwt = new JwtTokenProvider("mysecretkeymysecretkey");

    @Test
    public void testTokenValidationSuccess() {
        String token = jwt.generateToken(1L, "user@test.com", "USER");
        assertTrue(jwt.validateToken(token));
    }
}