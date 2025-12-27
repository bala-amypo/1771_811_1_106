package com.example.demo.auth;

import com.example.demo.security.JwtTokenProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtExtractUserIdTest {

    private final JwtTokenProvider jwt = new JwtTokenProvider("mysecretkeymysecretkey");

    @Test
    public void testExtractUserIdFromToken() {
        String token = jwt.generateToken(12L, "me@mail.com", "USER");
        Long id = jwt.extractUserId(token);
        assertEquals(12L, id);
    }
}