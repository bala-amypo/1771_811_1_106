package com.example.demo.auth;

import com.example.demo.security.JwtTokenProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtValidateTokenFailTest {

    private final JwtTokenProvider jwt = new JwtTokenProvider("mysecretkeymysecretkey");

    @Test
    public void testTokenValidationFails() {
        assertFalse(jwt.validateToken("BADTOKEN"));
    }
}