package com.example.bankcards.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Api")
public class TestSwagger {
    @GetMapping
    public String test() {
        return "Hello World!";
    }

}
