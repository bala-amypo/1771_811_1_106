package com.example.demo.dto;

public class AuthResponse {

    private String jwtToken;
    private String message;

    public AuthResponse() {
    }

    public AuthResponse(String jwtToken, String message) {
        this.jwtToken = jwtToken;
        this.message = message;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
