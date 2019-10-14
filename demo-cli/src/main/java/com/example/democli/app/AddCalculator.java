package com.example.democli.app;

public class AddCalculator implements Calculator {

    @Override
    public int calc(int a, int b) {
        return a + b;
    }

}
