package com.los.authservice.controller;


import com.los.authservice.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Map<String, String> login(
            @RequestParam String username,
            @RequestParam String password) {

        String token =
                authService.login(username, password);

        return Map.of(
                "token", token,
                "type", "Bearer"
        );
    }
}