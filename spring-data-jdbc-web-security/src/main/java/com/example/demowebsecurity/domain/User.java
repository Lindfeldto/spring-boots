package com.example.demowebsecurity.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public class User {
    @Id
    Long id;
    String username;
    String password;
    Boolean enabled = true;
    LocalDateTime createdAt = LocalDateTime.now();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String toString() {
        return "{id: " + id + ", username: " + username + "}";
    }
}
