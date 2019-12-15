package com.example.springdatajdbcrest.domain.onetomany.set;

import org.springframework.data.annotation.Id;

import lombok.Value;
import lombok.With;

@Value
@With
public class Skill { // One to Many Child

    private final @Id Long id;
    private final String name;

    public static Skill of(String name) {
        return new Skill(null, name);
    }

}
