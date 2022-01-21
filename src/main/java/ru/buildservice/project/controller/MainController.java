package ru.buildservice.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {
    //Начальные страницы
    @GetMapping("/")
    public String about() {
        return "index";
    }

    @GetMapping("/contacts")
    public String contact() {
        return "contacts";
    }
  
     @GetMapping("/login")
   public String getLoginView(){
       return "login";
   }




}