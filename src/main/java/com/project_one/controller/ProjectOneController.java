package com.project_one.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectOneController {
    @GetMapping("/hello-world")
    public String hello() {
        return "Hello World from Spring Boot! wow great";
    }

}
