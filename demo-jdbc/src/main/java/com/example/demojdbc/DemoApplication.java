package com.example.demojdbc;

import com.example.demojdbc.domain.Customer;
import com.example.demojdbc.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Customer created = customerRepository.save(new Customer(null, "John", "Doe"));

		System.out.printf("created: %s", created);

		customerRepository.findAll().forEach(System.out::println);
	}
}
