package com.example.springdatajdbcrest.domain.manytomany;

import org.springframework.data.annotation.Id;

import lombok.Value;
import lombok.With;

@Value
@With
public class Author {

    private final @Id Long id;
    private final String name;

    public static Author of(String name) {
        return new Author(null, name);
    }

}
