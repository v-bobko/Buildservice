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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "object_id")
    private Objects objects;

    public Photo() {
    }

    public Photo(String photo, Objects objects) {
        this.photo = photo;
        this.objects = objects;
    }
}
