package com.example.sirh_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SirhBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SirhBackendApplication.class, args);
    }

    @GetMapping("/")
    public String index(@RequestParam(value = "name", defaultValue = "User") String name) {
        return String.format("Hello %s! Welcome to SIRH.", name);
    }

}
