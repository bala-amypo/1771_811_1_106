package com.example.demo.service;

import com.example.demo.entity.AuthRequest;
import com.example.demo.entity.AuthResponse;

public interface AuthService {

    AuthResponse register(AuthRequest request);

    AuthResponse login(AuthRequest request);
}
