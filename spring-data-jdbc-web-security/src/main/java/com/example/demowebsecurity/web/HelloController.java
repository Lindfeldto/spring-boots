package com.example.demowebsecurity.web;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    String hello(Principal user) {
        // for demo
        System.out.println("In Controller, USER NAME: " + user.getName());

        return "hello"; // thymeleaf hello.html
    }

}
