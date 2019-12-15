package com.example.springdatajdbcrest.repository.onetomany.set;

import java.util.List;

import com.example.springdatajdbcrest.domain.onetomany.set.Person;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends CrudRepository<Person, Long> {
    
    @Query("SELECT * FROM person WHERE name = :name")
    List<Person> findByName(@Param("name") String name);

    // count child table
    @Query("select count(*) from skill")
    int countSkills();

}
