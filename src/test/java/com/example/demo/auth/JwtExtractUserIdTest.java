package com.example.demo.auth;

import com.example.demo.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JwtExtractUserIdTest {

    private final JwtTokenProvider jwt = new JwtTokenProvider();

    @Test
    public void testExtractUserIdFromToken() {
        // GIVEN
        Long expectedId = 12L;

        // WHEN
        String token = jwt.generateToken(expectedId.toString());
        Long extractedId = jwt.extractUserId(token);

        // THEN
        assertEquals(expectedId, extractedId, "User ID should match the extracted value from token");
    }
}
