package com.example.springdatajdbcrest.repository.onetomany.list;

import java.util.List;

import com.example.springdatajdbcrest.domain.onetomany.list.Customer;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("SELECT * FROM customer WHERE name = :name")
    List<Customer> findByName(@Param("name") String name);

    // count child table
    @Query("select count(*) from orders")
    int countOrders();

}
