package com.example.democli2;

import com.example.democli2.app.AddCalculator;
import com.example.democli2.app.ArgumentResolver;
import com.example.democli2.app.Calculator;
import com.example.democli2.app.Frontend;
import com.example.democli2.app.ScannerArgumentResolver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    Calculator calculator() {
        return new AddCalculator();
    }

    @Bean
    ArgumentResolver argumentResolver() {
        return new ScannerArgumentResolver();
    }

    @Bean
    Frontend frontend() {
        return new Frontend();
    }
}
