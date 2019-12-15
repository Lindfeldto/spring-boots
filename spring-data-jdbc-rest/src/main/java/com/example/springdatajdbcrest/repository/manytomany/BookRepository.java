package com.example.springdatajdbcrest.repository.manytomany;

import java.util.Set;

import com.example.springdatajdbcrest.domain.manytomany.Book;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends CrudRepository<Book, Long> {

    @Query("SELECT * FROM book b JOIN book_author ba ON b.id = ba.book WHERE ba.author_id = :id")
    Set<Book> findByAuthorId(@Param("id") Long id);

}
