package com.los.authservice.mock;

import com.los.authservice.model.User;

import java.util.List;

public class UserMockData {

    public static List<User> getUsers() {

        return List.of(

                new User(
                        "anurag",
                        "password123",
                        "USER"
                ),

                new User(
                        "rahul",
                        "rahul123",
                        "USER"
                ),

                new User(
                        "admin",
                        "admin123",
                        "ADMIN"
                )
        );
    }
}