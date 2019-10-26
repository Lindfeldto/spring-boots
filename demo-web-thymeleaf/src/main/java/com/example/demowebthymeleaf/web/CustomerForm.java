package com.example.demowebthymeleaf.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CustomerForm {
    @NotNull
    @Size(min = 1, max = 10)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 10)
    private String lastName;
}
