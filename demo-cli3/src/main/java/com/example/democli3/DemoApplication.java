package com.example.democli3;

import com.example.democli3.app.Argument;
import com.example.democli3.app.ArgumentResolver;
import com.example.democli3.app.Calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @EnableAutoConfiguration
// @ComponentScan
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	ArgumentResolver argumentResolver;
	@Autowired
	Calculator calculator;

	public static void main(String[] args) {
		// ApplicationContext context = SpringApplication.run(DemoApplication.class,
		// args);

		// Frontend frontend = context.getBean(Frontend.class);
		// frontend.run();

		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// System.out.println("Enter 2 numbers like 'a b'");

		Argument argument = argumentResolver.resolve(System.in);
		int result = calculator.calc(argument.getA(), argument.getB());

		System.out.println(result);
	}

}
