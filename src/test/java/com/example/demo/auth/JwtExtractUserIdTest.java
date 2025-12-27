package com.example.demo.auth;

import com.example.demo.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JwtExtractUserIdTest {

    private final JwtTokenProvider jwt = new JwtTokenProvider();

    @Test
    public void testExtractUserId() {
        // GIVEN
        Long userId = 25L;
        String role = "ADMIN";

        // WHEN
        String token = jwt.generateToken(userId, role);

        // THEN
        Long extractedUserId = jwt.extractUserId(token);
        assertNotNull(extractedUserId, "Extracted user ID should not be null");
        assertEquals(userId, extractedUserId, "Extracted user ID should match original user ID");
    }
}
