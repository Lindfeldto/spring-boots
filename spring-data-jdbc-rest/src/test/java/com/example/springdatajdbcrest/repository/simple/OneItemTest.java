package com.example.springdatajdbcrest.repository.simple;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import com.example.springdatajdbcrest.domain.simple.Item;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

/**
 * Sample Tests from official blog
 * https://spring.io/blog/2018/09/17/introducing-spring-data-jdbc
 */
@DataJdbcTest
class OneItemTest {

    @Autowired
    ItemRepository repository;

    @Test
    void simpleExample() {
        Item item = Item.of("Albert");
        Item saved = repository.save(item);

        assertThat(saved).isNotNull();
        assertThat(saved.getName()).isEqualTo("Albert");
        assertThat(repository.count()).isEqualTo(1);

        Item updating = saved.withName("Hans Albert");
        repository.save(updating);

        Optional<Item> reloaded = repository.findById(saved.getId());

        assertThat(reloaded).isNotEmpty();
        assertThat(reloaded.get().getName()).isEqualTo("Hans Albert");

        repository.delete(saved);

        assertThat(repository.count()).isEqualTo(0);
    }

    @Test
    void findByName() {
        Item saved = repository.save(Item.of("Albert"));

        assertThat(saved.getId()).isNotNull();

        // setting the Id to null and saving it again creates another row
        repository.save(saved.withId(null).withName("Bertram"));
        repository.save(saved.withId(null).withName("Beth"));

        // Select if name like %bert%
        List<Item> items = repository.findByName("bert");

        assertThat(items).hasSize(2);
        assertThat(items).extracting(Item::getName)
            .containsExactly("Albert", "Bertram");
    }

    @Test
    void saveMultiple() {
        Iterable<Item> saved = repository.saveAll(asList(Item.of("FOO"), Item.of("Bar")));

        assertThat(repository.count()).isEqualTo(2);

        assertThat(saved).extracting(item -> item.getId()).isNotNull();
    }

}
