package com.los.authservice.service.serviceImpl;


import com.los.authservice.mock.UserMockData;
import com.los.authservice.model.User;
import com.los.authservice.service.AuthService;
import com.los.authservice.service.JwtService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;

    public AuthServiceImpl(JwtService jwtService) {

        this.jwtService = jwtService;
    }

    @Override
    public String login(
            String username,
            String password) {

        System.out.println(
                "Username received: [" + username + "]"
        );

        System.out.println(
                "Password received: [" + password + "]"
        );

        User user = UserMockData.getUsers()
                .stream()
                .filter(u ->
                        u.getUsername().equals(username)
                                &&
                                u.getPassword().equals(password)
                )
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException(
                                "Invalid username or password"
                        )
                );

        System.out.println(
                "Login successful: "
                        + user.getUsername()
        );

        System.out.println(
                "User role: "
                        + user.getRole()
        );

        return jwtService.generateToken(
                user.getUsername(),
                user.getRole()
        );
    }
}