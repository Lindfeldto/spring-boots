package com.example.springdatajdbcrest.repository.manytomany;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.springdatajdbcrest.domain.manytomany.Author;
import com.example.springdatajdbcrest.domain.manytomany.Book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

@DataJdbcTest
class ManyToManyTests {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    /**
     * Sample Tests from official blog
     * https://spring.io/blog/2018/09/24/spring-data-jdbc-references-and-aggregates
     */
    @Test
    void saveAndDelete() {
        Author author1 = authorRepository.save(Author.of("Foo"));
        Author author2 = authorRepository.save(Author.of("Bar"));

        Book book = Book.of("Some Title").addAuthor(author1).addAuthor(author2);

        Book saved = bookRepository.save(book);

        assertThat(saved.getTitle()).isEqualTo("Some Title");

        assertThat(bookRepository.count()).isEqualTo(1);
        assertThat(authorRepository.count()).isEqualTo(2);

        bookRepository.deleteAll();

        assertThat(bookRepository.count()).isEqualTo(0);
        assertThat(authorRepository.count()).isEqualTo(2);

        debugPrint();
    }

    void debugPrint() {
        System.out.println("All Books: " + bookRepository.findAll());
        System.out.println("All Authors: " + authorRepository.findAll());

        for (Book b : bookRepository.findAll()) {
            System.out.println("Book: " + b.getTitle());
            System.out.println("Authors: " + authorRepository.findAllById(b.getAuthorIds()));
        }

        for (Author a : authorRepository.findAll()) {
            System.out.println("Author: " + a.getName());
            System.out.println("Books: " + bookRepository.findByAuthorId(a.getId()));
        }
    }

}
