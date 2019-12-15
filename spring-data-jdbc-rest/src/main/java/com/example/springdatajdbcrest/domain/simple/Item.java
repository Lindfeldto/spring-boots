package com.example.springdatajdbcrest.domain.simple;

import org.springframework.data.annotation.Id;

import lombok.Value;
import lombok.With;

@Value
@With
public class Item {

    private final @Id Long id;
    private final String name;

    public static Item of(String name) {
        return new Item(null, name);
    }

}
