package com.example.springdatajdbcrest.domain.manytomany;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Value;

@Value
@Table("book_author")
public class AuthorRef {
    private final Long author;

    public static AuthorRef of(Long author) {
        return new AuthorRef(author);
    }

}
