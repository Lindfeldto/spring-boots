package com.example.springdatajdbcrest.repository.onetomany.list;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import com.example.springdatajdbcrest.domain.onetomany.list.Customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

/**
 * Sample Tests from YouTube video
 * The New Kid on the Block: Spring Data JDBC
 * https://www.youtube.com/watch?v=AnIouYdwxo0
 * 
 * @see {@link OneToManySetTests}
 */
@DataJdbcTest
class OneToManyListTests {

    @Autowired
    CustomerRepository repository; // parent repository

    @Test
    void demoInNewKidVideo() {
        Customer customer = Customer.of("Foo").addOrder(100).addOrder(150).addOrder(100);
        Customer saved = repository.save(customer);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Foo");

        assertThat(repository.count()).isEqualTo(1);
        assertThat(repository.countOrders()).isEqualTo(3);

        // Load every time (no caching)
        Optional<Customer> one = repository.findById(saved.getId());
        Optional<Customer> two = repository.findById(saved.getId());

        assertThat(one).isNotSameAs(two);

        // UPDATE customer
        Customer updating = Customer.of("NewFoo", saved.getOrders());
        Customer updated = repository.save(updating);

        assertThat(updated.getName()).isEqualTo("NewFoo");

        // Clone entity (by setting id null, it assumes new entity)
        Customer clone = saved.withId(null);
        repository.save(clone);

        assertThat(repository.count()).isEqualTo(2);
        assertThat(repository.countOrders()).isEqualTo(4);

        System.out.println("CUSTOMERS: " + repository.findAll());

        repository.delete(saved);

        assertThat(saved.getId()).isNotNull(); // saved not changed after delete
        assertThat(repository.count()).isEqualTo(1);
        assertThat(repository.countOrders()).isEqualTo(2);

        System.out.println("CUSTOMERS: " + repository.findAll());
    }

    @Test
    void cloneCustomer() {
        Customer customer = Customer.of("Foo").addOrder(100).addOrder(150).addOrder(100);
        Customer saved = repository.save(customer);

        Customer clone = saved.withIdNull();
        Customer cloned = repository.save(clone);

        System.out.println(repository.findAll());

        assertThat(cloned.getId()).isNotEqualTo(saved.getId());
        assertThat(cloned.getName()).isEqualTo("Foo");

        assertThat(repository.count()).isEqualTo(2);
        assertThat(repository.countOrders()).isEqualTo(6);
    }

}
