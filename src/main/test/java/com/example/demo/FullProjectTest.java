package com.example.demo;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.config.SwaggerConfig;
import com.example.demo.service.UserService;

@SpringBootTest
public class FullProjectTest {

    @Autowired
    private SwaggerConfig swaggerConfig;

    @Autowired
    private UserService userService;

    // 1️⃣ Test: Swagger/OpenAPI config should exist
    @Test
    public void testSwaggerConfigPresent() {
        Assert.assertNotNull(swaggerConfig.customOpenAPI(), "OpenAPI config not found");
    }

    // 2️⃣ Test: Duplicate email registration must fail
    @Test
    public void testRegisterDuplicateEmailFails() {
        try {
            userService.register(new com.example.demo.entity.User(null, "duplicate@gmail.com", "pass", "USER"));
            userService.register(new com.example.demo.entity.User(null, "duplicate@gmail.com", "pass", "USER"));
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException expected) {
            // expected behavior
        }
    }

    // 3️⃣ Test: Register user success
    @Test
    public void testUserRegistrationCreatesUser() {
        var user = new com.example.demo.entity.User(null, "newuser@gmail.com", "123456", "USER");
        var saved = userService.register(user);
        Assert.assertNotNull(saved.getId(), "User ID not generated");
    }

    // 4️⃣ Test: Application loads core beans
    @Test
    public void testCoreBeansExist() {
        Assert.assertNotNull(userService);
        Assert.assertNotNull(swaggerConfig);
    }

    // 5️⃣ Test: Ensure password is saved but hidden later
    @Test
    public void testUserPasswordStored() {
        var user = new com.example.demo.entity.User(null, "pwtest@gmail.com", "mypassword", "USER");
        var saved = userService.register(user);
        Assert.assertEquals(saved.getPassword(), "mypassword");
    }

    // 6️⃣ Test: User lookup by email
    @Test
    public void testFindUserByEmail() {
        var user = userService.findByEmail("pwtest@gmail.com");
        Assert.assertNotNull(user, "User should be found");
    }

    // 7️⃣ Test: Prevent null email
    @Test
    public void testRegisterNullEmailFails() {
        try {
            userService.register(new com.example.demo.entity.User(null, null, "123", "USER"));
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException expected) {
            // expected behavior
        }
    }
}