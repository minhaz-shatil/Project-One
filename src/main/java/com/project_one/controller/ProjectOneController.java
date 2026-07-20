package com.project_one.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectOneController {
    @GetMapping("/hello-world")
    public String hello() {
        return "Hello World from Spring Boot! wow great.............from docker hub  my jenkins pass: 6d26882cad3046dd86c5b056adaca0f9";
    }

}
