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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.buildservice.project.Datetime;
import ru.buildservice.project.entity.*;
import ru.buildservice.project.entity.Objects;
import ru.buildservice.project.repository.*;
import ru.buildservice.project.security.auth.CustomUserDetailService;

import java.sql.Timestamp;
import java.util.*;

@Controller
public class CustomerController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private ObjectRepository objectRepository;
    @Autowired
    private CalendarServiceRepository calendarServiceRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private PhotoRepozitory photoRepozitory;


    @GetMapping("/customer/objects")
    public String customerObjects(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByUsername(auth.getName());
        List<Objects> objects = objectRepository.findByUsers(user);

        model.addAttribute("object", objects);
        model.addAttribute("user", user);

        return "customer/customer-objects";
    }

    @GetMapping("/customer/calendar/{id}")
    public String customerCalendar(@PathVariable(value = "id") int id, Model model) {

        Objects object = objectRepository.findById(id).orElseThrow();
        model.addAttribute("object", object);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByUsername(auth.getName());

        List<CalendarService> calendarServices = calendarServiceRepository.findByObjects(object);

        ArrayList<Integer> years = calendarServiceRepository.findYears();
        // нужно передать год он будет как запрос


        //если значение нулевое, тогда отобразить текущий месяц и текущий год иначе в соответствии со значением


        model.addAttribute("years", years);
        model.addAttribute("calendar", calendarServices);


        if (user.getUser_id() == object.getUsers().getUser_id()) {
            return "customer/customer-calendar";
        }
        return "redirect:/customer/objects";
    }

    @PostMapping("/customer/calendar/{id}")
    public String editObject(@PathVariable(value = "id") int id, @RequestParam String month, @RequestParam Integer year, Model model) {

        Objects object = objectRepository.findById(id).orElseThrow();
        model.addAttribute("object", object);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByUsername(auth.getName());
//
//        !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//                !!!!!!!!!!!!!!!!!!!!!
//                        !!!!!!!!!!!!!!!!!!!!!!!!!!!


        if (user.getUser_id() == object.getUsers().getUser_id()) {
            return "customer/customer-calendar";
        }
        return "redirect:/customer/objects";
    }


    @GetMapping("/customer/psd/{id}")
    public String customerPSD(@PathVariable(value = "id") int id, Model model) {
        Objects object = objectRepository.findById(id).orElseThrow();
        model.addAttribute("object", object);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByUsername(auth.getName());

        List<Projects> projects = projectRepository.findByObjects(object);

        model.addAttribute("projects", projects);

        if (user.getUser_id() == object.getUsers().getUser_id()) {
            return "customer/customer-psd";
        }
        return "redirect:/customer/objects";
    }


    @GetMapping("/customer/work/{id}")
    public String customerWork(@PathVariable(value = "id") int id, Model model) {
        Objects object = objectRepository.findById(id).orElseThrow();
        model.addAttribute("object", object);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByUsername(auth.getName());

        ArrayList<Integer> years = calendarServiceRepository.findYears();


        model.addAttribute("years", years);

        Datetime datetime = new Datetime();
        String month = datetime.extractMonth();
        int year = datetime.extractYear();
        model.addAttribute("month",month);
        model.addAttribute("year",year);

        List<CalendarService> calendarServices1 = calendarServiceRepository.findByObjectsAndMonthAndYearOrderByCalendarIdAsc(object, month, year);
        model.addAttribute("calendar", calendarServices1);

        List<Photo> photo = photoRepozitory.findByObjects(object);
        model.addAttribute("photo",photo);


        if (user.getUser_id() == object.getUsers().getUser_id()) {
            return "customer/customer-work";
        }
        return "redirect:/customer/objects";
    }

    @PostMapping("/customer/work/{id}")
    public String customerWorkCustomDate(@PathVariable(value = "id") int ids,  @RequestParam String month, @RequestParam int year, Model model) {
         Objects object = objectRepository.findById(ids).orElseThrow();
        model.addAttribute("object", object);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByUsername(auth.getName());
    model.addAttribute("month", month);
    model.addAttribute("year", year);


        ArrayList<Integer> years = calendarServiceRepository.findYears();
        List<CalendarService> calendarServices1 = calendarServiceRepository.findByObjectsAndMonthAndYearOrderByCalendarIdAsc(object, month, year);
        //List<CalendarService> calendarServices = calendarServiceRepository.findByObjects(object);


        model.addAttribute("years", years);


        model.addAttribute("calendar", calendarServices1);
        List<Photo> photo = photoRepozitory.findByObjects(object);
        model.addAttribute("photo",photo);


        if (user.getUser_id() == object.getUsers().getUser_id()) {
            return "customer/customer-work";
        }
        return "redirect:/customer/objects";
    }





    @PostMapping("/customer/edit-comment")
    public String createComment(@RequestParam Integer calendarId, @RequestParam String commentOfClient, Model model) {

        CalendarService calendarService = calendarServiceRepository.findById(calendarId).orElseThrow();

        calendarService.setCommentOfClient(commentOfClient);

        calendarServiceRepository.save(calendarService);
        int objectId = calendarService.getObjects().getObjectId();
        return "redirect:/customer/work/" + objectId;
    }


    @PostMapping("/customer/deleteComments")
    public String deleteComment(@RequestParam Integer calendarId, Model model) {

        CalendarService calendarService = calendarServiceRepository.findById(calendarId).orElseThrow();
        calendarService.setCommentOfClient(null);

        calendarServiceRepository.save(calendarService);
        int objectId = calendarService.getObjects().getObjectId();
        return "redirect:/customer/work/" + objectId;
    }


    @GetMapping("/customer/online/{id}")
    public String customerOnline(@PathVariable(value = "id") int id, Model model) {
        Objects object = objectRepository.findById(id).orElseThrow();
        model.addAttribute("object", object);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByUsername(auth.getName());

        if (user.getUser_id() == object.getUsers().getUser_id()) {
            return "customer/customer-online";
        }
        return "redirect:/customer/objects";
    }
}
