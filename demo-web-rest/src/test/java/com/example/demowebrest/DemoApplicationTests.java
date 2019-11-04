package com.example.demowebrest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import com.example.demowebrest.domain.Customer;
import com.example.demowebrest.repository.CustomerRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Data;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
		"spring.datasource.url=jdbc:log4jdbc:h2:mem:customers;DB_CLOSE_ON_EXIT=FALSE" })
public class DemoApplicationTests {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	TestRestTemplate restTemplate;

	Customer customer1;
	Customer customer2;

	ParameterizedTypeReference<Page<Customer>> pageCustomerResponseType;

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	static class Page<T> {
		private List<T> content;
		private int numberOfElements;
	}

	@Before
	public void setup() {
		customerRepository.deleteAll();

		customer1 = new Customer(null, "Taro", "Yamada");
		customer2 = new Customer(null, "Ichiro", "Suzuki");

		customerRepository.save(customer1);
		customerRepository.save(customer2);

		pageCustomerResponseType = new ParameterizedTypeReference<Page<Customer>>() {
		};
	}

	@Test
	public void testGetCustomers() throws Exception {
		ResponseEntity<Page<Customer>> response = restTemplate.exchange("/api/customers", HttpMethod.GET, null,
				pageCustomerResponseType);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getNumberOfElements()).isEqualTo(2);

		// getCustomers returns order by name so the order is reversed
		Customer c1 = response.getBody().getContent().get(0);
		assertThat(c1.getId()).isEqualTo(customer2.getId());
		assertThat(c1.getFirstName()).isEqualTo(customer2.getFirstName());
		assertThat(c1.getLastName()).isEqualTo(customer2.getLastName());

		Customer c2 = response.getBody().getContent().get(1);
		assertThat(c2.getId()).isEqualTo(customer1.getId());
		assertThat(c2.getFirstName()).isEqualTo(customer1.getFirstName());
		assertThat(c2.getLastName()).isEqualTo(customer1.getLastName());
	}

	@Test
	public void testPostCustomers() {
		Customer customer3 = new Customer(null, "Nobita", "Nobi");

		ResponseEntity<Customer> response = restTemplate.postForEntity("/api/customers", customer3, Customer.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		Customer customer = response.getBody();

		assertThat(customer.getId()).isNotNull();
		assertThat(customer.getFirstName()).isEqualTo(customer3.getFirstName());
		assertThat(customer.getLastName()).isEqualTo(customer3.getLastName());

		ResponseEntity<Page<Customer>> response2 = restTemplate.exchange("/api/customers", HttpMethod.GET, null,
				pageCustomerResponseType);
		assertThat(response2.getBody().getNumberOfElements()).isEqualTo(3);
	}

	@Test
	public void testDeleteCustomers() throws Exception {
		ResponseEntity<Void> response = restTemplate.exchange("/api/customers/{id}", HttpMethod.DELETE, null,
				Void.class, Collections.singletonMap("id", customer1.getId()));

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

		ResponseEntity<Page<Customer>> response2 = restTemplate.exchange("/api/customers", HttpMethod.GET, null,
				pageCustomerResponseType);
		assertThat(response2.getBody().getNumberOfElements()).isEqualTo(1);
	}

	@Test
	public void contextLoads() {
		// Leave this empty.
		// https://stackoverflow.com/questions/49345526
	}

}
