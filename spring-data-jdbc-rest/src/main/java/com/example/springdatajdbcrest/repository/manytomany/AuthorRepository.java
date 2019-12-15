package com.example.springdatajdbcrest.repository.manytomany;

import com.example.springdatajdbcrest.domain.manytomany.Author;

import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
