package com.example.demowebthymeleaf.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * sample test from https://spring.io/guides/gs/testing-web/
 * just a sanity check.
 */
@SpringBootTest
class SmokeTest {

    @Autowired
    private CustomerController controller;

    @Test
    void contextLoads() {
        // @SpringBootTest start a Spring application context.
        // To convince yourself that the context is creating your controller
        // you could add an assertion
        assertThat(controller).isNotNull();
    }

}
