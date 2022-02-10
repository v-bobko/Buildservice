package ru.buildservice.project.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "cameras")
public class Cameras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "camera_id")
    private int cameraId;


    @Column(name = "camera_url")
    private String cameraURL;

    @Column(name = "name_camera")
    private String nameCamera;

    @ManyToOne
    @JoinColumn(name = "object_id")
    private Objects objects;

    public Cameras() {
    }

    public Cameras(String cameraURL, String nameCamera, Objects objects) {
        this.cameraURL = cameraURL;
        this.nameCamera = nameCamera;
        this.objects = objects;
    }
}
