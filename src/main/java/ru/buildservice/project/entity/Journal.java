package ru.buildservice.project.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "journal")
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "journal_id")
    private int journalId;


    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;


    @Column(name = "FIO")
    private String fio;

    @Column(name = "time")
    private int time;

    @Column(name = "report")
    private String report;


    @ManyToOne
    @JoinColumn(name = "object_id")
    private Objects objects;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Users users;



    public Journal() {
    }

    public Journal(Date date, String fio, int time, String report, Objects objects, Users users) {
        this.date = date;
        this.fio = fio;
        this.time = time;
        this.report = report;
        this.objects = objects;
        this.users = users;
    }
}

