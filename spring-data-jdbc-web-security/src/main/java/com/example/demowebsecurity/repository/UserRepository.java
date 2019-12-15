package com.example.demowebsecurity.repository;

import com.example.demowebsecurity.domain.User;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT count(*) FROM users, authorities WHERE authorities.authority = :authority AND users.username = authorities.username GROUP BY authority")
    Long countUserByAuthority(@Param("authority") String authority);

}
