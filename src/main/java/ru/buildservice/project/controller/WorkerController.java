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
import org.springframework.web.multipart.MultipartFile;
import ru.buildservice.project.Datetime;
import ru.buildservice.project.entity.*;
import ru.buildservice.project.repository.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Controller
public class WorkerController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CalendarServiceRepository calendarServiceRepository;
    @Autowired
    private ObjectRepository objectRepository;
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private JournalRepository journalRepository;
    @Autowired
    private ProjectRepository projectRepository;

    //  Страница с объектами
    @GetMapping("/worker/objects")
    public String workerObjects(Model model) {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByUsername(auth.getName());

        List<Integer> userID = calendarServiceRepository.findObjectbyUserId(user.getUser_id());

        List<Objects> object = new ArrayList<>();
        userID.forEach(us -> object.add(objectRepository.findByObjectId(us)));

        model.addAttribute("user", user);
        model.addAttribute("objects", object);

        return "worker/worker-objects";
    }


    @GetMapping("/worker/work/{id}")
    public String workerWork(@PathVariable(value = "id") int id, @RequestParam(required = false, value = "month") String curMonth, @RequestParam(required = false, value = "year") Integer curYear, Model model) {
        Objects object = objectRepository.findById(id).orElseThrow();
        model.addAttribute("object", object);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByUsername(auth.getName());
        model.addAttribute("user", user);
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

        List<CalendarService> calendarServices1 = calendarServiceRepository.findByObjectsAndMonthAndYearAndUsersOrderByCalendarIdAsc(object, month, year, user);
        model.addAttribute("calendar", calendarServices1);

        List<Photo> photo = photoRepository.findByObjectsAndMonthAndYearOrderByPhotoIdDesc(object, month, year);
        model.addAttribute("photo", photo);

        return "worker/worker-work";

    }


    @PostMapping("/worker/work/addphoto/{id}")
    public String addPhoto(@PathVariable(value = "id") int id, @RequestParam(value = "photo") MultipartFile file, @RequestParam(value = "month") String month, @RequestParam(value = "year") Integer year, Model model) {
        Objects objects = objectRepository.findById(id).orElseThrow();
        Photo photo = new Photo();
        photo.setObjects(objects);
        photo.setMonth(month);
        photo.setYear(year);

        photoRepository.save(photo);

        CalendarComment calendarComment = new CalendarComment();
        calendarComment.setYear(year);

        calendarComment.setMonth(month);
        calendarComment.setObjects(objects);

        return "worker/worker-work";
    }

    @GetMapping("/worker/psd/{id}")
    public String workerPSD(@PathVariable(value = "id") int id, Model model) {
        Objects object = objectRepository.findById(id).orElseThrow();
        model.addAttribute("object", object);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByUsername(auth.getName());
        model.addAttribute("user", user);

        List<Projects> projects = projectRepository.findByObjects(object);
        model.addAttribute("projects", projects);

        return "worker/worker-psd";
    }


    @GetMapping("/worker/journal/{id}")
    public String workerJournal(@PathVariable(value = "id") int id, Model model) {
        Objects object = objectRepository.findById(id).orElseThrow();
        model.addAttribute("object", object);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByUsername(auth.getName());
        model.addAttribute("user", user);

        List<Journal> journals = journalRepository.findByObjectsAndUsersOrderByDateDescJournalIdDesc(object, user);
        model.addAttribute("journal", journals);

        return "worker/worker-journal";
    }

    @PostMapping("/worker/journal/{id}/addStr")
    public String addStringWorker(@PathVariable(value = "id") int id,
                                  @RequestParam(value = "date") Date date,
                                  @RequestParam(value = "fio") String fio,
                                  @RequestParam(value = "time") Integer time,
                                  @RequestParam(value = "report") String report,
                                  @RequestParam(value = "userId") Integer userId) {

        Objects object = objectRepository.findById(id).orElseThrow();
        Users user=userRepository.findById(userId).orElseThrow();

        Journal journal=new Journal(date,fio,time,report,object,user);
        journalRepository.save(journal);

        return "redirect:/worker/journal/{id}";
    }

    @PostMapping("/worker/journal/{id}/dellStr")
    public String dellStringWorker(@PathVariable(value = "id") int id,Model model) {

        Objects object = objectRepository.findById(id).orElseThrow();
        model.addAttribute("object", object);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByUsername(auth.getName());

        Journal journal=journalRepository.findLastJournalByUsers(user.getUser_id());
        journalRepository.delete(journal);

        return "redirect:/worker/journal/{id}";
    }

    @GetMapping("/worker/application/{id}")
    public String workerApplication(@PathVariable(value = "id") int id, Model model) {
        Objects object = objectRepository.findById(id).orElseThrow();
        model.addAttribute("object", object);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByUsername(auth.getName());
        model.addAttribute("user", user);

        return "worker/worker-application";
    }


}
