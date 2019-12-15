package com.example.springdatajdbcrest.domain.manytomany;

import static java.util.Collections.unmodifiableSet;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;

import lombok.Value;
import lombok.With;

@Value
@With
public class Book {

    private final @Id Long id;
    private final String title;
    private final Set<AuthorRef> authors;

    public static Book of(String title) {
        return new Book(null, title, unmodifiableSet(new HashSet<>()));
    }

    public Book addAuthor(Author author) {
        // clone authors
        Set<AuthorRef> authors_ = new HashSet<>();
        authors_.addAll(authors);

        authors_.add(AuthorRef.of(author.getId()));
        return this.withAuthors(authors_);
    }

    public Set<Long> getAuthorIds() {
        return this.authors.stream()
            .map(AuthorRef::getAuthor)
            .collect(Collectors.toSet());
    }

}
