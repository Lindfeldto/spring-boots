package com.example.demojpa;

import com.example.demojpa.domain.Customer;
import com.example.demojpa.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

		customerRepository.findAllOrderByName().forEach(System.out::println);

		// Paging
		Pageable pageable = PageRequest.of(0, 3, Sort.by("firstName", "lastName"));
		Page<Customer> page = customerRepository.findAll(pageable);
		// Page<Customer> page =
		// customerRepository.findAllOrderByNamePageable(pageable);

		System.out.printf("size: %d\nnumber: %d\n", page.getSize(), page.getNumber());
		System.out.printf("total pages: %d\ntotal elem: %d\n", page.getTotalPages(), page.getTotalElements());
		page.getContent().forEach(System.out::println);
	}

}
