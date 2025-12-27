package com.example.demo.auth;

import com.example.demo.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JwtValidateTokenSuccessTest {

    private final JwtTokenProvider jwt = new JwtTokenProvider();

    @Test
    public void testValidTokenPassesValidation() {
        // GIVEN: create a valid token
        Long userId = 1L;
        String role = "USER";
        String token = jwt.generateToken(userId, role);

        // WHEN: validate the generated token
        boolean isValid = jwt.validateToken(token);

        // THEN
        assertTrue(isValid, "Valid token should return true");
    }
}
