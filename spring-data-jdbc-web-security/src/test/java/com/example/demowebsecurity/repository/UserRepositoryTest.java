package com.example.demowebsecurity.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demowebsecurity.JdbcConfig;
import com.example.demowebsecurity.domain.Authority;
import com.example.demowebsecurity.domain.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@DataJdbcTest
@Transactional
@ContextConfiguration(classes = JdbcConfig.class)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Test
    public void testSave() {
        Authority saved = authorityRepository.save(new Authority("user", "ROLE_TEST"));
        assertThat(saved).isNotNull();

        System.out.println("AUTHORITIES: " + authorityRepository.findAll());

        User savedUser = userRepository.save(new User("user", "pass"));
        assertThat(savedUser).isNotNull();

        System.out.println("USERS: " + userRepository.findAll());
    }

    @Test
    @Sql(statements = "INSERT INTO users (username, password, enabled) VALUES ('U', 'P', TRUE)")
    @Sql(statements = "INSERT INTO authorities (username, authority) VALUES ('U', 'ROLE_ADMIN')")
    void testCustomQuery() {
        Iterable<User> users = userRepository.findAll();
        assertThat(users).hasSize(4);

        Long count = userRepository.countUserByAuthority("ROLE_ADMIN");
        assertThat(count).isEqualTo(2);
    }

}
