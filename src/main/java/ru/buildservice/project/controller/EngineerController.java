package ru.buildservice.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.buildservice.project.entity.Objects;
import ru.buildservice.project.entity.Roles;
import ru.buildservice.project.entity.Users;
import ru.buildservice.project.repository.ObjectRepository;
import ru.buildservice.project.repository.RolesRepository;
import ru.buildservice.project.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class EngineerController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private ObjectRepository objectRepository;


    //Личный кабинет инженера


    @GetMapping("/engineer/objects")
    public String engineerObjects(Model model) {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        Users user= userRepository.findByUsername(auth.getName());
        List<Objects> objects = objectRepository.findAll();
//        ArrayList<Objects> listOfObjects = new ArrayList<>();
//        objects.forEach(listOfObjects::add);
        model.addAttribute("object",objects);
        model.addAttribute("user",user);
        return "engineer/engineer-objects";
    }

    @GetMapping("/engineer/calendar/{id}")
    public String engineerCalendar(@PathVariable(value = "id") int id, Model model) {
        Objects object=objectRepository.findById(id).orElseThrow();
        model.addAttribute("object",object);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String name=auth.getName();
        model.addAttribute("nameEngineer",name);
        return "engineer/engineer-calendar";
    }

    @GetMapping("/engineer/psd/{id}")
    public String engineerPSD(@PathVariable(value = "id") int id, Model model) {
        Objects object=objectRepository.findById(id).orElseThrow();
        model.addAttribute("object",object);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String name=auth.getName();
        model.addAttribute("nameEngineer",name);
        return "engineer/engineer-psd";
    }

    @GetMapping("/engineer/work/{id}")
    public String engineerWork(@PathVariable(value = "id") int id, Model model) {
        Objects object=objectRepository.findById(id).orElseThrow();
        model.addAttribute("object",object);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String name=auth.getName();
        model.addAttribute("nameEngineer",name);
        return "engineer/engineer-work";
    }

    @GetMapping("/engineer/journal/{id}")
    public String engineerJournal(@PathVariable(value = "id") int id, Model model) {
        Objects object=objectRepository.findById(id).orElseThrow();
        model.addAttribute("object",object);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String name=auth.getName();
        model.addAttribute("nameEngineer",name);
        return "engineer/engineer-journal";
    }

    @GetMapping("/engineer/online/{id}")
    public String engineerOnline(@PathVariable(value = "id") int id, Model model) {
        Objects object=objectRepository.findById(id).orElseThrow();
        model.addAttribute("object",object);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String name=auth.getName();
        model.addAttribute("nameEngineer",name);
        return "engineer/engineer-online";
    }

    @GetMapping("/engineer/application/{id}")
    public String engineerApplication(@PathVariable(value = "id") int id, Model model) {
        Objects object=objectRepository.findById(id).orElseThrow();
        model.addAttribute("object",object);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String name=auth.getName();
        model.addAttribute("nameEngineer",name);
        return "engineer/engineer-application";
    }


}

