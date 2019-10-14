package com.example.democli;

import com.example.democli.app.Argument;
import com.example.democli.app.ArgumentResolver;
import com.example.democli.app.Calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@Import(AppConfig.class)
public class DemoCliApplication {

	public static void main(String[] args) {
		// SpringApplication.run(DemoCliApplication.class, args);

		ApplicationContext context = SpringApplication.run(DemoCliApplication.class, args);

		ArgumentResolver argumentResolver = context.getBean(ArgumentResolver.class);
		Argument argument = argumentResolver.resolve(System.in);

		Calculator calculator = context.getBean(Calculator.class);
		int result = calculator.calc(argument.getA(), argument.getB());

		System.out.println(result);
	}

}
