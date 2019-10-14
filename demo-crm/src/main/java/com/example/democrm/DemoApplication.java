package com.example.democrm;

import com.example.democrm.domain.Customer;
import com.example.democrm.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	CustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerService.save(new Customer(1, "Foo", "Fo"));
		customerService.save(new Customer(2, "Bar", "Ba"));
		customerService.save(new Customer(3, "Baz", "Bz"));

		customerService.findAll().forEach(System.out::println);
	}

}
