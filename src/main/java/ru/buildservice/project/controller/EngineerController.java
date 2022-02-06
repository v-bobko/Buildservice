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
import ru.buildservice.project.Datetime;
import ru.buildservice.project.entity.*;
import ru.buildservice.project.repository.*;

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
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private CalendarServiceRepository calendarServiceRepository;
    @Autowired
    private CalendarCommentRepository calendarCommentRepository;


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
    public String engineerCalendar(@PathVariable(value = "id") int id, @RequestParam(required = false,value = "year") Integer curYear, Model model) {
        Objects object=objectRepository.findById(id).orElseThrow();
        model.addAttribute("object",object);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String name=auth.getName();
        model.addAttribute("nameEngineer",name);
        ArrayList<Integer> years = calendarServiceRepository.findYears();
        model.addAttribute("years", years);

        Integer year;

        if(curYear==null){
            Datetime datetime = new Datetime();
            year = datetime.extractYear();
        } else year= curYear;

        model.addAttribute("year",year);

        //Работы, которые занес инженер

        List<CalendarService> jan = calendarServiceRepository.findByObjectsAndMonthAndYearOrderByCalendarIdAsc(object, "Январь", year);
        model.addAttribute("jan", jan);
        List<CalendarService> feb = calendarServiceRepository.findByObjectsAndMonthAndYearOrderByCalendarIdAsc(object, "Февраль", year);
        model.addAttribute("feb", feb);
        List<CalendarService> mar = calendarServiceRepository.findByObjectsAndMonthAndYearOrderByCalendarIdAsc(object, "Март", year);
        model.addAttribute("mar", mar);
        List<CalendarService> apr = calendarServiceRepository.findByObjectsAndMonthAndYearOrderByCalendarIdAsc(object, "Апрель", year);
        model.addAttribute("apr", apr);
        List<CalendarService> may = calendarServiceRepository.findByObjectsAndMonthAndYearOrderByCalendarIdAsc(object, "Май", year);
        model.addAttribute("may", may);
        List<CalendarService> jun = calendarServiceRepository.findByObjectsAndMonthAndYearOrderByCalendarIdAsc(object, "Июнь", year);
        model.addAttribute("jun", jun);
        List<CalendarService> jul = calendarServiceRepository.findByObjectsAndMonthAndYearOrderByCalendarIdAsc(object, "Июль", year);
        model.addAttribute("jul", jul);
        List<CalendarService> aug = calendarServiceRepository.findByObjectsAndMonthAndYearOrderByCalendarIdAsc(object, "Август", year);
        model.addAttribute("aug", aug);
        List<CalendarService> sep = calendarServiceRepository.findByObjectsAndMonthAndYearOrderByCalendarIdAsc(object, "Сентябрь", year);
        model.addAttribute("sep", sep);
        List<CalendarService> oct = calendarServiceRepository.findByObjectsAndMonthAndYearOrderByCalendarIdAsc(object, "Октябрь", year);
        model.addAttribute("oct", oct);
        List<CalendarService> nov = calendarServiceRepository.findByObjectsAndMonthAndYearOrderByCalendarIdAsc(object, "Ноябрь", year);
        model.addAttribute("nov", nov);
        List<CalendarService> dec = calendarServiceRepository.findByObjectsAndMonthAndYearOrderByCalendarIdAsc(object, "Декабрь", year);
        model.addAttribute("dec", dec);

        //Работы, которые занес заказчик в поле комментарии
        List<CalendarComment> janCom = calendarCommentRepository.findByObjectsAndMonthAndYearOrderByCommentIdAsc(object, "Январь", year);
        model.addAttribute("janCom", janCom);
        List<CalendarComment> febCom = calendarCommentRepository.findByObjectsAndMonthAndYearOrderByCommentIdAsc(object, "Февраль", year);
        model.addAttribute("febCom", febCom);
        List<CalendarComment> marCom = calendarCommentRepository.findByObjectsAndMonthAndYearOrderByCommentIdAsc(object, "Март", year);
        model.addAttribute("marCom", marCom);
        List<CalendarComment> aprCom = calendarCommentRepository.findByObjectsAndMonthAndYearOrderByCommentIdAsc(object, "Апрель", year);
        model.addAttribute("aprCom", aprCom);
        List<CalendarComment> mayCom = calendarCommentRepository.findByObjectsAndMonthAndYearOrderByCommentIdAsc(object, "Май", year);
        model.addAttribute("mayCom", mayCom);
        List<CalendarComment> junCom = calendarCommentRepository.findByObjectsAndMonthAndYearOrderByCommentIdAsc(object, "Июнь", year);
        model.addAttribute("junCom", junCom);
        List<CalendarComment> julCom = calendarCommentRepository.findByObjectsAndMonthAndYearOrderByCommentIdAsc(object, "Июль", year);
        model.addAttribute("julCom", julCom);
        List<CalendarComment> augCom = calendarCommentRepository.findByObjectsAndMonthAndYearOrderByCommentIdAsc(object, "Август", year);
        model.addAttribute("augCom", augCom);
        List<CalendarComment> sepCom = calendarCommentRepository.findByObjectsAndMonthAndYearOrderByCommentIdAsc(object, "Сентябрь", year);
        model.addAttribute("sepCom", sepCom);
        List<CalendarComment> octCom = calendarCommentRepository.findByObjectsAndMonthAndYearOrderByCommentIdAsc(object, "Октябрь", year);
        model.addAttribute("octCom", octCom);
        List<CalendarComment> novCom = calendarCommentRepository.findByObjectsAndMonthAndYearOrderByCommentIdAsc(object, "Ноябрь", year);
        model.addAttribute("novCom", novCom);
        List<CalendarComment> decCom = calendarCommentRepository.findByObjectsAndMonthAndYearOrderByCommentIdAsc(object, "Декабрь", year);
        model.addAttribute("decCom", decCom);

        return "engineer/engineer-calendar";
    }

    @PostMapping("/engineer/calendar/{id}")
    public String engineerCalendarAddYearPost(@PathVariable(value = "id") int id, @RequestParam(required = false,value = "newYear") Integer newYear, Model model){
        //Добавление года
        CalendarService calendarService=new CalendarService();
        calendarService.setYear(newYear);
        calendarServiceRepository.save(calendarService);
        return "redirect:/engineer/calendar/{id}";
    }


    @GetMapping("/engineer/psd/{id}")
    public String engineerPSD(@PathVariable(value = "id") int id, Model model) {
        Objects object=objectRepository.findById(id).orElseThrow();
        model.addAttribute("object",object);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String name=auth.getName();
        model.addAttribute("nameEngineer",name);

        List<Projects> projects = projectRepository.findByObjects(object);

        model.addAttribute("projects", projects);

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

