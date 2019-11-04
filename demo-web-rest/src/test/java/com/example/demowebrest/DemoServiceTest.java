package com.example.demowebrest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import com.example.demowebrest.domain.Customer;
import com.example.demowebrest.repository.CustomerRepository;
import com.example.demowebrest.service.CustomerService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

// Note: @SpringBootTest will load all classes
// therefore use ContextConfiguration instead to load specific classes
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CustomerService.class})
public class DemoServiceTest {

    @Autowired
    private CustomerService service;

    // note: @SpyBean is something between Autowired and MockBean
    // https://www.youtube.com/watch?v=Ekr4jxOIf4c

    @MockBean
    private CustomerRepository repository;

    @Before
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
