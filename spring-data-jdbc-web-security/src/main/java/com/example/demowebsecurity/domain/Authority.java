package com.example.demowebsecurity.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("authorities")
public class Authority {
    @Id
    Long id;
    String username;
    String authority;
    LocalDateTime createdAt = LocalDateTime.now();

    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
	}
    
    public String toString() {
        return "{" + id + ": " + username + "/" + authority + "}";
    }
}
