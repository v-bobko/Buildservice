package ru.buildservice.project.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private int photoId;


    @Column(name = "photo")
    private String photo;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "object_id")
    private Objects objects;

    public Photo() {
    }

    public Photo(String photo, String month, int year, Objects objects) {
        this.photo = photo;
        this.month = month;
        this.year = year;
        this.objects = objects;
    }
}
