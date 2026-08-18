package com.los.authservice.mock;

import com.los.authservice.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class UserMockData {

    public static List<User> getUsers() {

        LocalDateTime now = LocalDateTime.now();

        return List.of(

                new User(
                        101L,
                        "9876543210",
                        "anurag@gmail.com",
                        "anurag",
                        "password123",
                        "USER",
                        "LEAD_READ,LEAD_CREATE,APPLICATION_READ",
                        true,
                        true,
                        true,
                        now,
                        now,
                        false
                ),

                new User(
                        102L,
                        "9876543211",
                        "rahul@gmail.com",
                        "rahul",
                        "rahul123",
                        "USER",
                        "LEAD_READ,LEAD_CREATE",
                        true,
                        true,
                        true,
                        now,
                        now,
                        false
                ),

                new User(
                        103L,
                        "9876543212",
                        "admin@gmail.com",
                        "admin",
                        "admin123",
                        "ADMIN",
                        "ALL",
                        true,
                        true,
                        true,
                        now,
                        now,
                        false
                )
        );
    }

    private UserMockData() {
    }
}