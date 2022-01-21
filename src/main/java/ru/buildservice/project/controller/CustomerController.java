package ru.buildservice.project.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    //Личный кабинет клиента

    @GetMapping("/customer/objects")
    public String customerObjects() {
        return "customer/customer-objects";
    }

    @GetMapping("/customer/calendar")
    public String customerCalendar() {
        return "customer/customer-calendar";
    }

    @GetMapping("/customer/psd")
    public String customerPSD() {
        return "customer/customer-psd";
    }

    @GetMapping("/customer/work")
    public String customerWork() {
        return "customer/customer-work";
    }

    @GetMapping("/customer/online")
    public String customerOnline() {
        return "customer/customer-online";
    }
}
