package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse register(AuthRequest request) {

        // check if user exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return new AuthResponse("User already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return new AuthResponse("Registration successful");
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());

        if (!optionalUser.isPresent()) {
            return new AuthResponse("User not found");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new AuthResponse("Invalid password");
        }

        return new AuthResponse("Login successful");
    }
}
