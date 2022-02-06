package ru.buildservice.project.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "calendar_comment")
public class CalendarComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int commentId;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private int year;


    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "object_id")
    private Objects objects;


    public CalendarComment() {
    }

    public CalendarComment(String month, int year, String comment, Objects objects) {
        this.month = month;
        this.year = year;
        this.comment = comment;
        this.objects = objects;
    }
}

