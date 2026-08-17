package com.los.authservice.service;

public interface AuthService {
    String login(
            String username,
            String password
    );
}
