package com.example.demowebsecurity.repository;

import com.example.demowebsecurity.domain.Authority;

import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
}
