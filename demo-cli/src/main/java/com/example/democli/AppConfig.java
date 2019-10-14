package com.example.democli;

import com.example.democli.app.AddCalculator;
import com.example.democli.app.ArgumentResolver;
import com.example.democli.app.Calculator;
import com.example.democli.app.ScannerArgumentResolver;

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
}
