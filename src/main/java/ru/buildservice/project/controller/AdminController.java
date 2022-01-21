package ru.buildservice.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
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
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private ObjectRepository objectRepository;


    //Личный кабинет инженера-администратора


    @GetMapping("/engineer/choice")
    public String engineerChoice() {
        return "engineer/choice";
    }


    @GetMapping("/engineer/adminObjects")
    public String adminObjectsPage(Model model) {
        //        Отображение объектов
        List<Objects> objects = objectRepository.findUsernameWithObjects();
        ArrayList<Objects> listOfObjects = new ArrayList<>();
        objects.forEach(listOfObjects::add);
        model.addAttribute("objects", listOfObjects);

//        Отображение заказчиков
        List<Users> users = userRepository.findRoleUser("CUSTOMER");
        ArrayList<Users> listOfUsers = new ArrayList<>();
        users.forEach(listOfUsers::add);
        model.addAttribute("users", listOfUsers);
//        Отображение рабочих
        List<Users> workers = userRepository.findRoleUser("WORKER");
        ArrayList<Users> listOfWorkers = new ArrayList<>();
        workers.forEach(listOfWorkers::add);
        model.addAttribute("workers", listOfWorkers);
        //        Отображение рабочих
        List<Users> admins = userRepository.findRoleUser("ADMIN");
        ArrayList<Users> listOfAdmins = new ArrayList<>();
        admins.forEach(listOfAdmins::add);
        model.addAttribute("admins", listOfAdmins);
        return "engineer/admin-objects";

    }
    @PostMapping("/engineer/adminObjects")
    public String editLk(@RequestParam String username, @RequestParam String password,
                         @RequestParam Integer userId,@RequestParam Optional<String> booln,@RequestParam Optional<String> boolp, Model model) {


        Users user = userRepository.findById(userId).orElseThrow();

        if (booln.isPresent()&!username.equals("")){
            user.setUsername(username);
            userRepository.save(user);
        }
        if (boolp.isPresent()&!password.equals("")){
            user.setPassword(password);
            userRepository.save(user);
        }


        return "redirect:/engineer/adminObjects";
}

    @PostMapping("/engineer/adminObjects/editOb")
    public String editObject(@RequestParam Integer objectId, @RequestParam Integer userId, Model model) {


        Objects object = objectRepository.findById(objectId).orElseThrow();

        Users user = userRepository.findById(userId).orElseThrow();
        object.setUsers(user);

        objectRepository.save(object);


        return "redirect:/engineer/adminObjects";
    }

    @PostMapping("/engineer/adminObjects/delete")
    public String deleteLk( @RequestParam Integer userIdDelete, Model model) {

        Users user = userRepository.findById(userIdDelete).orElseThrow();

        userRepository.delete(user);

        return "redirect:/engineer/adminObjects";
    }

   @PostMapping("/engineer/adminObjects/deleteObject")
   public String deleteObject( @RequestParam Integer objectIdDelete, Model model) {

       Objects object = objectRepository.findById(objectIdDelete).orElseThrow();

       objectRepository.delete(object);

       return "redirect:/engineer/adminObjects";
   }



    @GetMapping("/engineer/admin-create-object")
    public String adminCreateObjectPage(Model model) {

        List<Users> users = userRepository.findRoleUser("CUSTOMER");
        ArrayList<Users> listOfUsers = new ArrayList<>();
        users.forEach(listOfUsers::add);
        model.addAttribute("users", listOfUsers);
        return "engineer/admin-create-object-page";
    }

    @PostMapping("/engineer/admin-create-object")
    public String createObject(@RequestParam String nameObject, @RequestParam String nameUserId, Model model) {

        Users users = userRepository.findByUsername(nameUserId);
        Objects objects = new Objects(nameObject,users);
        objectRepository.save(objects);
      //
        return "redirect:/engineer/adminObjects";
    }


    @GetMapping("/engineer/admin-create-account")
    public String adminCreateAccountPage() {
        return "engineer/admin-create-account-page";
    }

    @PostMapping("/engineer/admin-create-account")
    public String createAccount(@RequestParam String username, @RequestParam String password, @RequestParam String role,
                                Model model) {

        Roles roles = rolesRepository.findByRoleName(role);
        Users user = new Users(username, password, roles);
        userRepository.save(user);


        return "redirect:/engineer/adminObjects";
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

    @GetMapping("/engineer/journal")
    public String engineerJournal() {
        return "engineer/engineer-journal";
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

