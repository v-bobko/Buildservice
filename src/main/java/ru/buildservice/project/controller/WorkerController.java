package ru.buildservice.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import ru.buildservice.project.repository.CalendarServiceRepository;
import ru.buildservice.project.repository.ObjectRepository;
import ru.buildservice.project.repository.PhotoRepository;
import ru.buildservice.project.repository.UserRepository;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


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
@Value("${filePathPhoto}")
   private String filePathPhoto;

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
    public String workerWork(@PathVariable(value = "id") int id,
                             @RequestParam(required = false, value = "month") String curMonth,
                             @RequestParam(required = false, value = "year") Integer curYear, Model model) {
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
    public String addPhoto(@PathVariable(value = "id") int id,
                           @RequestParam(value = "photo") MultipartFile[] files,
                           @RequestParam(value = "month") String month,
                           @RequestParam(value = "year") Integer year, Model model) throws IOException {

        Objects object = objectRepository.findById(id).orElseThrow();


        for (MultipartFile file: files
             ) {



        if (!file.isEmpty()) {

            String dotExtendName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));// Получить расширение
            String fileName = UUID.randomUUID().toString().replace("-", "") + dotExtendName;// Имя UUID + расширение.



            File path = new File(filePathPhoto);
            if (!path.exists()) {
                path.mkdirs();
            }
            // Загрузить
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(filePathPhoto + fileName)));
            out.write(file.getBytes());
            out.flush();
            out.close();

            String filenameBD = "/images/" + fileName;

            Photo photo = new Photo();
            photo.setObjects(object);
            photo.setMonth(month);
            photo.setYear(year);
            photo.setPhoto(filenameBD);
            photoRepository.save(photo);
            photoRepository.save(photo);
        }}



        model.addAttribute("object",object);
        return "redirect:/worker/work/{id}";
    }


    @GetMapping("/worker/psd")
    public String workerPSD() {
        return "worker/worker-psd";
    }


    @GetMapping("/worker/journal")
    public String workerJournal() {
        return "worker/worker-journal";
    }

    @GetMapping("/worker/application")
    public String workerApplication() {
        return "worker/worker-application";
    }


}
