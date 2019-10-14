package com.example.democli2;

import com.example.democli2.app.Frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@Import(AppConfig.class)
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

		Frontend frontend = context.getBean(Frontend.class);
		frontend.run();
	}

}
