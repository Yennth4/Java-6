package com.yennth.demo.demo.B6_SecurityConfig_Authen_Author.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("home")
    public String home() {
        return "hello world home ne";
    }
}
