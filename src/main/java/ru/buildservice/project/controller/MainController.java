package ru.buildservice.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @GetMapping("/")
    public String about() {
        return "index";
    }
    @GetMapping("/contacts")
    public String contact() {
        return "contacts";
    }



}