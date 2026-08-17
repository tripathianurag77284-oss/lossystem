package com.los.authservice.service;

public interface JwtService {
    String generateToken(String username, String role);
}
