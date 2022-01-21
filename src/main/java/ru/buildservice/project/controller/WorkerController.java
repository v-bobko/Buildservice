package ru.buildservice.project.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkerController {

    //Личный кабинет рабочего

    @GetMapping("/worker/objects")
    public String workerObjects() {
        return "worker/worker-objects";
    }

    @GetMapping("/worker/psd")
    public String workerPSD() {
        return "worker/worker-psd";
    }

    @GetMapping("/worker/work")
    public String workerWork() {
        return "worker/worker-work";
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
