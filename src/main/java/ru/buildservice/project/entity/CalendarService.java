package ru.buildservice.project.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "calendar_service")
public class CalendarService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id")
    private int calendarId;


    @Temporal(TemporalType.DATE)
    @Column(name = "date_start")
    private java.util.Date dateStart;


    @Column(name = "task")
    private String task;

    @Column(name = "comment_of_worker")
    private String commentOfWorker;

    @Column(name = "problems")
    private String problems;

    @Column(name = "comment_of_client")
    private String commentOfClient;

    @Column(name = "comment_of_engineer")
    private String commentOfEngineer;

    @ManyToOne
    @JoinColumn(name = "object_id")
    private Objects objects;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;


    public CalendarService() {
    }

    public CalendarService(Date dateStart, String task, String commentOfWorker, String problems, String commentOfClient, String commentOfEngineer, Objects objects, Users users) {
        this.dateStart = dateStart;
        this.task = task;
        this.commentOfWorker = commentOfWorker;
        this.problems = problems;
        this.commentOfClient = commentOfClient;
        this.commentOfEngineer = commentOfEngineer;
        this.objects = objects;
        this.users = users;
    }
}

