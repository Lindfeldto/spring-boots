package com.example.demojdbc.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import com.example.demojdbc.domain.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustomerRepository {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    SimpleJdbcInsert insert;

    // private static final RowMapper<Customer> customerMapper = (rs, rowNum) -> {
    // return new Customer(rs.getInt("id"), rs.getString("first_name"),
    // rs.getString("last_name"));
    // };

    private static final RowMapper<Customer> customerMapper = new BeanPropertyRowMapper<>(Customer.class);

    @PostConstruct
    public void init() {
        insert = new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate()).withTableName("customers")
                .usingGeneratedKeyColumns("id");
    }

    public List<Customer> findAll() {
        String sql = "SELECT id, first_name, last_name FROM customers ORDER BY id";
        List<Customer> customers = jdbcTemplate.query(sql, customerMapper);

        return customers;
    }

    public Customer findOne(Integer id) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        String sql = "SELECT id, first_name, last_name FROM customers WHERE id = :id";

        return jdbcTemplate.queryForObject(sql, param, customerMapper);
    }

    public Customer save(Customer customer) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(customer);
        String sql = "UPDATE customers SET first_name = :firstName, last_name = :lastName WHERE id = :id";

        if (customer.getId() == null) {
            Number key = insert.executeAndReturnKey(param);
            customer.setId(key.intValue());
        } else {
            jdbcTemplate.update(sql, param);
        }

        return customer;
    }

    public void delete(Integer id) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        String sql = "DELETE from customers WHERE id = :id";

        jdbcTemplate.update(sql, param);
    }
}
