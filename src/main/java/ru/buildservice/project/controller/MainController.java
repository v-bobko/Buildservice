package ru.buildservice.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    //Личный кабинет инженера-администратора

    @GetMapping("/engineer/choice")
    public String engineerChoice() {
        return "engineer/choice";
    }

    @GetMapping("/engineer/adminObjects")
    public String adminObjectsPage() {
        return "engineer/admin-objects";
    }

    @GetMapping("/engineer/admin-create-object")
    public String adminCreateObjectPage() {
        return "engineer/admin-create-object-page";
    }

    @GetMapping("/engineer/admin-create-account")
    public String adminCreateAccountPage() {
        return "engineer/admin-create-account-page";
    }

    @GetMapping("/engineer/objects")
    public String engineerObjects() {
        return "engineer/engineer-objects";
    }

    @GetMapping("/engineer/calendar")
    public String engineerCalendar() {
        return "engineer/engineer-calendar";
    }

    @GetMapping("/engineer/psd")
    public String engineerPSD() {
        return "engineer/engineer-psd";
    }

    @GetMapping("/engineer/work")
    public String engineerWork() {
        return "engineer/engineer-work";
    }

    @GetMapping("/engineer/online")
    public String engineerOnline() {
        return "engineer/engineer-online";
    }

    @GetMapping("/engineer/application")
    public String engineerApplication() {
        return "engineer/engineer-application";
    }

}