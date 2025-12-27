package com.example.demo.auth;

import com.example.demo.security.JwtTokenProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtGenerateTokenTest {

    private final JwtTokenProvider jwt = new JwtTokenProvider("mysecretkeymysecretkey");

    @Test
    public void testTokenGenerationValid() {
        String token = jwt.generateToken(1L, "vidhya@gmail.com", "USER");
        assertNotNull(token);
    }
}