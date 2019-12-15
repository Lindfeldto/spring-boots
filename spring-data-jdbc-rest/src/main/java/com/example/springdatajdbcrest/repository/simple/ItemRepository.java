package com.example.springdatajdbcrest.repository.simple;

import java.util.List;

import com.example.springdatajdbcrest.domain.simple.Item;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends CrudRepository<Item, Long> {

    @Query("SELECT id, name FROM item where upper(name) like '%' || upper(:name) || '%' ")
    List<Item> findByName(@Param("name") String name);

}
