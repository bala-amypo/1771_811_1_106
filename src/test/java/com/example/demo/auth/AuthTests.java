package com.example.demo.auth;

import com.example.demo.dto.AuthRequest;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.AuthController;

public class AuthTests {

    private UserService userService;
    private JwtTokenProvider jwtProvider;
    private AuthController authController;

    @BeforeClass
    public void setup() {
        userService = Mockito.mock(UserService.class);
        jwtProvider = new JwtTokenProvider();
        authController = new AuthController(userService, jwtProvider);
    }

    // 1️⃣ Register user success
    @Test
    public void testRegisterUserSuccess() {
        User input = new User(null, "new@mail.com", "pwd123", "USER");
        User saved = new User(1L, "new@mail.com", "pwd123", "USER");

        Mockito.when(userService.register(input)).thenReturn(saved);

        ResponseEntity<?> response = authController.register(input);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    // 2️⃣ Duplicate email fails
    @Test
    public void testRegisterDuplicateEmailFails() {
        User existing = new User(1L, "dup@mail.com", "pwd", "USER");

        Mockito.when(userService.findByEmail("dup@mail.com")).thenReturn(existing);

        ResponseEntity<?> response = authController.register(existing);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    // 3️⃣ Login success → should return JWT token
    @Test
    public void testLoginSuccessReturnsToken() {
        User saved = new User(1L, "x@mail.com", "pwd", "USER");
        Mockito.when(userService.findByEmail("x@mail.com")).thenReturn(saved);

        AuthRequest login = new AuthRequest("x@mail.com", "pwd", "USER");
        ResponseEntity<?> response = authController.login(login);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    // 4️⃣ Login wrong password unauthorized
    @Test
    public void testLoginWrongPasswordUnauthorized() {
        User saved = new User(1L, "y@mail.com", "right", "USER");
        Mockito.when(userService.findByEmail("y@mail.com")).thenReturn(saved);

        AuthRequest wrong = new AuthRequest("y@mail.com", "wrong", "USER");
        ResponseEntity<?> response = authController.login(wrong);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }

    // 5️⃣ JWT token generation valid
    @Test
    public void testTokenGenerationValid() {
        String token = jwtProvider.generateToken(1L, "abc@mail.com", "USER");
        Assert.assertNotNull(token);
        Assert.assertFalse(token.isEmpty());
    }

    // 6️⃣ JWT validation success
    @Test
    public void testTokenValidationSuccess() {
        String token = jwtProvider.generateToken(10L, "valid@mail.com", "ADMIN");
        Long id = jwtProvider.getUserIdFromToken(token);
        Assert.assertEquals(id.longValue(), 10L);
    }

    // 7️⃣ JWT validation failure (invalid token)
    @Test
    public void testTokenValidationFails() {
        String badToken = "abc.def.ghi"; // invalid format
        try {
            jwtProvider.getUserIdFromToken(badToken);
            Assert.fail("Should throw token exception");
        } catch (Exception e) {
            Assert.assertTrue(true); // expected
        }
    }
}