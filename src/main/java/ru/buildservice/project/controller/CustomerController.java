package ru.buildservice.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.buildservice.project.entity.Objects;
import ru.buildservice.project.entity.Users;
import ru.buildservice.project.repository.ObjectRepository;
import ru.buildservice.project.repository.RolesRepository;
import ru.buildservice.project.repository.UserRepository;
import ru.buildservice.project.security.auth.CustomUserDetailService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private ObjectRepository objectRepository;


    @GetMapping("/customer/objects")
    public String customerObjects( Model model) {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        Users user= userRepository.findByUsername(auth.getName());
        List<Objects> objects = objectRepository.findByUsers(user);
        ArrayList<Objects> listOfObjects = new ArrayList<>();
        objects.forEach(listOfObjects::add);
        model.addAttribute("object",listOfObjects);
        model.addAttribute("user",user);

        return "customer/customer-objects";
    }

    @GetMapping("/customer/calendar/{id}")
    public String customerCalendar(@PathVariable(value = "id") int id, Model model) {

        Objects object=objectRepository.findById(id).orElseThrow();
        model.addAttribute("object",object);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        Users user= userRepository.findByUsername(auth.getName());

        if(user.getUser_id()==object.getUsers().getUser_id()) {
            return "customer/customer-calendar";
        }
        return "redirect:/customer/objects";
    }

    @GetMapping("/customer/psd/{id}")
    public String customerPSD(@PathVariable(value = "id") int id, Model model) {
        Objects object=objectRepository.findById(id).orElseThrow();
        model.addAttribute("object",object);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        Users user= userRepository.findByUsername(auth.getName());

        if(user.getUser_id()==object.getUsers().getUser_id()) {
            return "customer/customer-psd";
        }
        return "redirect:/customer/objects";
    }

    @GetMapping("/customer/work/{id}")
    public String customerWork(@PathVariable(value = "id") int id, Model model) {
        Objects object=objectRepository.findById(id).orElseThrow();
        model.addAttribute("object",object);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        Users user= userRepository.findByUsername(auth.getName());

        if(user.getUser_id()==object.getUsers().getUser_id()) {
            return "customer/customer-work";
        }
        return "redirect:/customer/objects";
    }

    @GetMapping("/customer/online/{id}")
    public String customerOnline(@PathVariable(value = "id") int id, Model model) {
        Objects object=objectRepository.findById(id).orElseThrow();
        model.addAttribute("object",object);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        Users user= userRepository.findByUsername(auth.getName());

        if(user.getUser_id()==object.getUsers().getUser_id()) {
            return "customer/customer-online";
        }
        return "redirect:/customer/objects";
    }
}
