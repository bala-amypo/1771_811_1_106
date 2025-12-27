package com.example.demo.auth;

import com.example.demo.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JwtValidateTokenFailTest {

    private final JwtTokenProvider jwt = new JwtTokenProvider();

    @Test
    public void testInvalidTokenFailsValidation() {
        // GIVEN
        String invalidToken = "this_is_not_a_valid_jwt";

        // WHEN
        boolean isValid = jwt.validateToken(invalidToken);

        // THEN
        assertFalse(isValid, "Invalid token should return false");
    }
}
