package com.example.demowebthymeleaf.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import com.example.demowebthymeleaf.domain.Customer;
import com.example.demowebthymeleaf.repository.CustomerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = CustomerService.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerService service;

    // Note: @SpyBean is mixture of Autowired and MockBean
    //       It only replace some of methods in the class.
    // see Defog Tech - Spring Boot Testing basics
    // https://www.youtube.com/watch?v=Ekr4jxOIf4c

    @MockBean
    private CustomerRepository repository;

    @BeforeEach
    public void setup() {
        // setup mock repository
        Optional<Customer> mockCustomer = Optional.ofNullable(new Customer(1, "Foo", "Fo"));
        Mockito.when(repository.findById(1)).thenReturn(mockCustomer);
    }

    @Test
    public void findOne() {
        final Customer customer = service.findOne(1);

        assertThat(customer.getId()).isEqualTo(1);
        assertThat(customer.getFirstName()).isEqualTo("Foo");
        assertThat(customer.getLastName()).isEqualTo("Fo");
    }

}
