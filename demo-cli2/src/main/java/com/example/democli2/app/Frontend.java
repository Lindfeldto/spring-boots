package com.example.democli2.app;

import org.springframework.beans.factory.annotation.Autowired;

public class Frontend {
    @Autowired
    ArgumentResolver argumentResolver;
    @Autowired
    Calculator calculator;

    public void run() {
        Argument argument = argumentResolver.resolve(System.in);
        int result = calculator.calc(argument.getA(), argument.getB());

        System.out.println(result);
    }
}
