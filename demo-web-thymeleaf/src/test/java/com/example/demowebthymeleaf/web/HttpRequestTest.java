package com.example.demowebthymeleaf.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

/**
 * sample test from https://spring.io/guides/gs/testing-web/
 * test the behavior of our application.
 * start the server with a random port
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        String url = "http://localhost:" + port + "/customers";
        String res = this.restTemplate.getForObject(url, String.class);

        System.out.println(res);
        assertThat(res).contains("Nobita");
    }

}
