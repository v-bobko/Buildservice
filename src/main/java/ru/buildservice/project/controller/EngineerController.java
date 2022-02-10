package ru.buildservice.project.controller;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.buildservice.project.Datetime;
import ru.buildservice.project.entity.*;
import ru.buildservice.project.repository.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


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
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private JournalRepository journalRepository;
    @Autowired
    private CameraRepository cameraRepository;
    @Autowired
    private EstimateRepository estimateRepository;




    //Личный кабинет инженера


    @GetMapping("/engineer/objects")
    public String engineerObjects(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByUsername(auth.getName());
        List<Objects> objects = objectRepository.findAll();
//        ArrayList<Objects> listOfObjects = new ArrayList<>();
//        objects.forEach(listOfObjects::add);
        model.addAttribute("object", objects);
        model.addAttribute("user", user);
        return "engineer/engineer-objects";
    }

    @GetMapping("/engineer/calendar/{id}")
    public String engineerCalendar(@PathVariable(value = "id") int id, @RequestParam(required = false, value = "year") Integer curYear, Model model) {
        Objects object = objectRepository.findById(id).orElseThrow();
        model.addAttribute("object", object);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("nameEngineer", name);
        ArrayList<Integer> years = calendarServiceRepository.findYears();
        model.addAttribute("years", years);

        Integer year;

        if (curYear == null) {
            Datetime datetime = new Datetime();
            year = datetime.extractYear();
        } else year = curYear;

        model.addAttribute("year", year);
        // Все рабочие
        List<Users> workers = userRepository.findRoleUser("WORKER");
        model.addAttribute("workers", workers);

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
    public String engineerCalendarAddYearPost(@PathVariable(value = "id") int id, @RequestParam(required = false, value = "newYear") Integer newYear, Model model) {
        //Добавление года
        CalendarService calendarService = new CalendarService();
        calendarService.setYear(newYear);
        calendarServiceRepository.save(calendarService);
        return "redirect:/engineer/calendar/{id}";
    }

    @PostMapping("/engineer/calendar/{id}/addwork")
    public String engineerCalendarAddWorkPost(@PathVariable(value = "id") int id,
                                              @RequestParam(required = false, value = "work") String work,
                                              @RequestParam(value = "month") String month,
                                              @RequestParam(value = "year") Integer year,
                                              @RequestParam(value = "workername") String workername, Model model) {

        if (!workername.isEmpty() & !work.isEmpty()) {
            Objects object = objectRepository.findById(id).orElseThrow();
            Users worker = userRepository.findByUsername(workername);
            CalendarService calendarService = new CalendarService();
            calendarService.setYear(year);
            calendarService.setMonth(month);
            calendarService.setObjects(object);
            calendarService.setUsers(worker);
            calendarService.setTask(work);
            calendarServiceRepository.save(calendarService);
        }
        return "redirect:/engineer/calendar/{id}/?year=" + year;
    }

    @PostMapping("/engineer/calendar/{id}/deleteTasks")
    public String deleteTasks(@PathVariable(value = "id") int id,
                              @RequestParam(required = false, value = "task") List<Integer> task,
                              @RequestParam(value = "year") Integer year,
                              Model model) {

        for (Integer el : task) {
            CalendarService calendarService = calendarServiceRepository.findByCalendarIdOrderByCalendarIdAsc(el);
            calendarServiceRepository.delete(calendarService);
        }

        return "redirect:/engineer/calendar/{id}/?year=" + year;
    }


    @GetMapping("/engineer/psd/{id}")
    public String engineerPSD(@PathVariable(value = "id") int id, Model model) {
        Objects object = objectRepository.findById(id).orElseThrow();
        model.addAttribute("object", object);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("nameEngineer", name);

        List<Projects> projects = projectRepository.findByObjects(object);
        model.addAttribute("projects", projects);
        List<Estimates> estimates = estimateRepository.findByObjects(object);
        model.addAttribute("estimates", estimates);

        return "engineer/engineer-psd";
    }

    @PostMapping("/engineer/psd/{id}/addDoc")
    public String engineerAddDoc(@PathVariable(value = "id") int id,
                                 @RequestParam(value = "fileURL") MultipartFile fileURL,
                                 @RequestParam(value = "fileName") String nameFile,
                                 Model model) throws IOException {

        Objects object = objectRepository.findById(id).orElseThrow();

        if (!fileURL.isEmpty()&!nameFile.isEmpty()) {

            String dotExtendName = fileURL.getOriginalFilename().substring(fileURL.getOriginalFilename().lastIndexOf("."));// Получить расширение
            String fileName = UUID.randomUUID().toString().replace("-", "") + dotExtendName;// Имя UUID + расширение.
            String filePath = "C:\\Users\\79818\\Desktop\\Diplom\\src\\main\\resources\\static\\files\\";

            File path = new File(filePath);
            if (!path.exists()) {
                path.mkdirs();
            }
            // Загрузить
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(filePath + fileName)));
            out.write(fileURL.getBytes());
            out.flush();
            out.close();

            String filenameBD = "/files/" + fileName;

            Projects project=new Projects(filenameBD,nameFile,object);
            projectRepository.save(project);
        }
        return "redirect:/engineer/psd/{id}";
    }

    @PostMapping("/engineer/psd/{id}/dellDoc")
    public String engineerDellDoc(@PathVariable(value = "id") int id,
                                  @RequestParam(required = false, value = "files") List<Integer> files) {

        for (Integer fileId : files) {
            Projects project = projectRepository.findById(fileId).orElseThrow();
            projectRepository.delete(project);
        }
        return "redirect:/engineer/psd/{id}";
    }

    @PostMapping("/engineer/psd/{id}/addSmet")
    public String engineerAddSmet(@PathVariable(value = "id") int id,
                                 @RequestParam(value = "fileURL") MultipartFile fileURL,
                                 @RequestParam(value = "fileName") String nameFile,
                                 Model model) throws IOException {

        Objects object = objectRepository.findById(id).orElseThrow();

        if (!fileURL.isEmpty()&!nameFile.isEmpty()) {

            String dotExtendName = fileURL.getOriginalFilename().substring(fileURL.getOriginalFilename().lastIndexOf("."));// Получить расширение
            String fileName = UUID.randomUUID().toString().replace("-", "") + dotExtendName;// Имя UUID + расширение.
            String filePath = "C:\\Users\\79818\\Desktop\\Diplom\\src\\main\\resources\\static\\files\\";

            File path = new File(filePath);
            if (!path.exists()) {
                path.mkdirs();
            }
            // Загрузить
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(filePath + fileName)));
            out.write(fileURL.getBytes());
            out.flush();
            out.close();

            String filenameBD = "/files/" + fileName;

            Estimates estimate =new Estimates(filenameBD,nameFile,object);
            estimateRepository.save(estimate);
        }
        return "redirect:/engineer/psd/{id}";
    }

    @PostMapping("/engineer/psd/{id}/dellSmet")
    public String engineerDellSmet(@PathVariable(value = "id") int id,
                                  @RequestParam(required = false, value = "files") List<Integer> files) {

        for (Integer fileId : files) {
            Estimates estimate  = estimateRepository.findById(fileId).orElseThrow();
            estimateRepository.delete(estimate);
        }
        return "redirect:/engineer/psd/{id}";
    }

    @GetMapping("/engineer/work/{id}")
    public String engineerWork(@PathVariable(value = "id") int id,
                               @RequestParam(required = false, value = "month") String curMonth,
                               @RequestParam(required = false, value = "year") Integer curYear,
                               Model model) {
        Objects object = objectRepository.findById(id).orElseThrow();
        model.addAttribute("object", object);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("nameEngineer", name);
        Users user = userRepository.findByUsername(name);

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

        return "engineer/engineer-work";
    }

    @PostMapping("/engineer/edit-comment")
    public String createCommentEngineer(@RequestParam Integer calendarId,
                                        @RequestParam String commentOfEngineer,
                                        Model model) {

        CalendarService calendarService = calendarServiceRepository.findById(calendarId).orElseThrow();

        calendarService.setCommentOfEngineer(commentOfEngineer);

        calendarServiceRepository.save(calendarService);
        int objectId = calendarService.getObjects().getObjectId();

        String month = URLEncoder.encode(calendarService.getMonth(), StandardCharsets.UTF_8);
        int year = calendarService.getYear();
        return "redirect:/engineer/work/" + objectId + "?month=" + month + "&year=" + year;
    }


    @PostMapping("/engineer/deleteComments")
    public String deleteCommentEngineer(@RequestParam Integer calendarId,
                                        Model model) {

        CalendarService calendarService = calendarServiceRepository.findById(calendarId).orElseThrow();
        calendarService.setCommentOfEngineer(null);

        calendarServiceRepository.save(calendarService);
        int objectId = calendarService.getObjects().getObjectId();

        String month = URLEncoder.encode(calendarService.getMonth(), StandardCharsets.UTF_8);
        int year = calendarService.getYear();
        return "redirect:/engineer/work/" + objectId + "?month=" + month + "&year=" + year;
    }

    @GetMapping("/engineer/journal/{id}")
    public String engineerJournal(@PathVariable(value = "id") int id,
                                  Model model) {
        Objects object = objectRepository.findById(id).orElseThrow();
        model.addAttribute("object", object);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("nameEngineer", name);

        List<Journal> journals = journalRepository.findByObjects(object);
        model.addAttribute("journal", journals);

        return "engineer/engineer-journal";
    }

    @GetMapping("/engineer/online/{id}")
    public String engineerOnline(@PathVariable(value = "id") int id, Model model) {
        Objects object = objectRepository.findById(id).orElseThrow();
        model.addAttribute("object", object);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("nameEngineer", name);

        List<Cameras> cameras = cameraRepository.findByObjectsOrderByCameraIdAsc(object);
        model.addAttribute("cameras", cameras);
        return "engineer/engineer-online";
    }

    @PostMapping("/engineer/online/{id}/addCamera")
    public String addCameraEngineer(@PathVariable(value = "id") int id,
                                    @RequestParam(value = "nameCamera") String nameCamera,
                                    @RequestParam(value = "urlCamera") String urlCamera,
                                    Model model) {

        Objects object = objectRepository.findById(id).orElseThrow();
        Cameras camera = new Cameras(urlCamera, nameCamera, object);

        cameraRepository.save(camera);
        return "redirect:/engineer/online/{id}";
    }

    @PostMapping("/engineer/online/{id}/deleteCamera")
    public String deleteCameraEngineer(@PathVariable(value = "id") int id,
                                       @RequestParam(required = false, value = "cameraId") List<Integer> cameraId) {

        Objects object = objectRepository.findById(id).orElseThrow();

        for (Integer idCam : cameraId) {
            Cameras camera = cameraRepository.findById(idCam).orElseThrow();
            ;
            cameraRepository.delete(camera);
        }

        return "redirect:/engineer/online/{id}";
    }


    @GetMapping("/engineer/application/{id}")
    public String engineerApplication(@PathVariable(value = "id") int id, Model model) {
        Objects object = objectRepository.findById(id).orElseThrow();
        model.addAttribute("object", object);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("nameEngineer", name);
        return "engineer/engineer-application";
    }


}

