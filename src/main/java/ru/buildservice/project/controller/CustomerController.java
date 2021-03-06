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
import ru.buildservice.project.entity.Objects;
import ru.buildservice.project.repository.*;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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
    private CalendarCommentRepository calendarCommentRepository;
    @Autowired
    private EstimateRepository estimateRepository;
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private CameraRepository cameraRepository;


    //  Страница с объектами
    @GetMapping("/customer/objects")
    public String customerObjects(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByUsername(auth.getName());
        List<Objects> objects = objectRepository.findByUsers(user);

        model.addAttribute("object", objects);
        model.addAttribute("user", user);

        return "customer/customer-objects";
    }

    // очень некрасивый способ отображения работ по месяцам, но если это работает то это не глупо
    @GetMapping("/customer/calendar/{id}")
    public String customerCalendar(@PathVariable(value = "id") int id, @RequestParam(required = false, value = "year") Integer curYear, Model model) {

        Objects object = objectRepository.findById(id).orElseThrow();
        model.addAttribute("object", object);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByUsername(auth.getName());

        List<CalendarService> calendarServices = calendarServiceRepository.findByObjects(object);

        ArrayList<Integer> years = calendarServiceRepository.findYears();
        Integer year;
        if (curYear == null) {
            Datetime datetime = new Datetime();
            year = datetime.extractYear();
        } else year = curYear;

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


        //Работы, вносит закзачик
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


        model.addAttribute("years", years);
        model.addAttribute("year", year);
        model.addAttribute("calendar", calendarServices);


        if (user.getUser_id() == object.getUsers().getUser_id()) {
            return "customer/customer-calendar";
        }
        return "redirect:/customer/objects";
    }

    // Добавление комментария клиентом в календаре работ
    @PostMapping("/customer/calendar/addComment/{id}")
    public String createCustomerComment(@PathVariable(value = "id") int id, @RequestParam(value = "month") String month, @RequestParam(value = "commentOfClient") String commentOfClient, @RequestParam(value = "year") Integer year, Model model) {
        Objects objects = objectRepository.findById(id).orElseThrow();
        CalendarComment calendarComment = new CalendarComment();
        calendarComment.setYear(year);
        calendarComment.setComment(commentOfClient);
        calendarComment.setMonth(month);
        calendarComment.setObjects(objects);
        calendarCommentRepository.save(calendarComment);


        return "redirect:/customer/calendar/{id}/?year=" + year;
    }


    // Удаление комментария клиентом в календаре работ
    @PostMapping("/customer/calendar/{id}/deleteCom")
    public String deleteCustomerComment(@PathVariable(value = "id") int id,
                                        @RequestParam(required = false, value = "com") List<Integer> comments,
                                        @RequestParam(value = "year") Integer year,
                                        Model model) {
        Objects object = objectRepository.findById(id).orElseThrow();
        model.addAttribute("object", object);

        for (Integer el : comments) {
            CalendarComment calendarComment = calendarCommentRepository.findByCommentId(el);
            calendarCommentRepository.delete(calendarComment);
        }

        return "redirect:/customer/calendar/{id}/?year=" + year;
    }


    //  страница со сметами и проектами
    @GetMapping("/customer/psd/{id}")
    public String customerPSD(@PathVariable(value = "id") int id, Model model) {
        Objects object = objectRepository.findById(id).orElseThrow();
        model.addAttribute("object", object);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByUsername(auth.getName());

        List<Projects> projects = projectRepository.findByObjects(object);
        model.addAttribute("projects", projects);
        List<Estimates> estimates = estimateRepository.findByObjects(object);
        model.addAttribute("estimates", estimates);

        if (user.getUser_id() == object.getUsers().getUser_id()) {
            return "customer/customer-psd";
        }
        return "redirect:/customer/objects";
    }

    // страница с работами за месяц
    @GetMapping("/customer/work/{id}")
    public String customerWork(@PathVariable(value = "id") int id, @RequestParam(required = false, value = "month") String curMonth, @RequestParam(required = false, value = "year") Integer curYear, Model model) {
        Objects object = objectRepository.findById(id).orElseThrow();
        model.addAttribute("object", object);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByUsername(auth.getName());

        ArrayList<Integer> years = calendarServiceRepository.findYears();
        model.addAttribute("years", years);
        String month;
        Integer year;

        if (curMonth == null) {
            Datetime datetime = new Datetime();
            month = datetime.extractMonth();
            year = datetime.extractYear();
        } else {
            year = curYear;
            month = curMonth;
        }


        model.addAttribute("month", month);
        model.addAttribute("year", year);

        List<CalendarService> calendarServices1 = calendarServiceRepository.findByObjectsAndMonthAndYearOrderByCalendarIdAsc(object, month, year);
        model.addAttribute("calendar", calendarServices1);

        List<Photo> photo = photoRepository.findByObjectsAndMonthAndYearOrderByPhotoIdDesc(object, month, year);
        model.addAttribute("photo", photo);


        if (user.getUser_id() == object.getUsers().getUser_id()) {
            return "customer/customer-work";
        }
        return "redirect:/customer/objects";
    }

    // Добавление комментария на странице с работами за месяц
    @PostMapping("/customer/edit-comment")
    public String createComment(@RequestParam Integer calendarId, @RequestParam String commentOfClient, Model model) {

        CalendarService calendarService = calendarServiceRepository.findById(calendarId).orElseThrow();

        calendarService.setCommentOfClient(commentOfClient);

        calendarServiceRepository.save(calendarService);
        int objectId = calendarService.getObjects().getObjectId();

        String month = URLEncoder.encode(calendarService.getMonth(), StandardCharsets.UTF_8);
        int year = calendarService.getYear();

        return "redirect:/customer/work/" + objectId + "?month=" + month + "&year=" + year;
    }


    // Удаление комментария на странице с работами за месяц
    @PostMapping("/customer/deleteComments")
    public String deleteComment(@RequestParam Integer calendarId, Model model) {

        CalendarService calendarService = calendarServiceRepository.findById(calendarId).orElseThrow();
        calendarService.setCommentOfClient(null);

        calendarServiceRepository.save(calendarService);
        int objectId = calendarService.getObjects().getObjectId();

        String month = URLEncoder.encode(calendarService.getMonth(), StandardCharsets.UTF_8);
        int year = calendarService.getYear();

        return "redirect:/customer/work/" + objectId + "?month=" + month + "&year=" + year;
    }


    @GetMapping("/customer/online/{id}")
    public String customerOnline(@PathVariable(value = "id") int id, Model model) {
        Objects object = objectRepository.findById(id).orElseThrow();
        model.addAttribute("object", object);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByUsername(auth.getName());

        List<Cameras> cameras = cameraRepository.findByObjectsOrderByCameraIdAsc(object);
        model.addAttribute("cameras", cameras);

        if (user.getUser_id() == object.getUsers().getUser_id()) {
            return "customer/customer-online";
        }
        return "redirect:/customer/objects";
    }
}
