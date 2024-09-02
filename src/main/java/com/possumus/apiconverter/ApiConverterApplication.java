package com.possumus.apiconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class ApiConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiConverterApplication.class, args);
    }
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("value", 0);
        return "index";
    }
}
