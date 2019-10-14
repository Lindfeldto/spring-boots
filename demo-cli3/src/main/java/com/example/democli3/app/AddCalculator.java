package com.example.democli3.app;

import org.springframework.stereotype.Component;

@Component
public class AddCalculator implements Calculator {

    @Override
    public int calc(int a, int b) {
        return a + b;
    }

}
