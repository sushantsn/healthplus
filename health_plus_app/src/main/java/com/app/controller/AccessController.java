package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AccessController {
    @GetMapping("/unauthorized")
    public String unauthorized() {
        return "error/403";
    }
}
