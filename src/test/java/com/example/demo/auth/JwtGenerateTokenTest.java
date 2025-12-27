package com.example.demo.auth;

import com.example.demo.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JwtGenerateTokenTest {

    private final JwtTokenProvider jwt = new JwtTokenProvider();

    @Test
    public void testTokenIsGenerated() {
        // GIVEN
        Long userId = 10L;
        String role = "USER";

        // WHEN
        String token = jwt.generateToken(userId, role);

        // THEN
        assertNotNull(token, "Generated token should not be null");
        assertFalse(token.isEmpty(), "Generated token should not be empty");
        assertTrue(token.startsWith("ey"), "Token should look like a JWT");
    }
}
