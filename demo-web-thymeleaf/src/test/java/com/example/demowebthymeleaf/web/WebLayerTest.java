package com.example.demowebthymeleaf.web;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.example.demowebthymeleaf.domain.Customer;
import com.example.demowebthymeleaf.service.CustomerService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

/**
 * sample test from https://spring.io/guides/gs/testing-web/
 * here Spring Boot is only instantiating the web layer, not the whole context.
 */
// @SpringBootTest
@WebMvcTest(CustomerController.class)
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService service;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        // Data initialization will not happen so need some mock data here.
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "Foo", "Bar"));

        when(service.findAll()).thenReturn(customers);

        ResultActions result = this.mockMvc.perform(get("/customers"));
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(content().string(containsString("Foo")));
    }

}
