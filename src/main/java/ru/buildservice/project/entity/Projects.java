package ru.buildservice.project.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "projects")
public class Projects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private int projectId;


    @Column(name = "project")
    private String project;

    @Column(name = "name_of_project")
    private String nameOfProject;

    @ManyToOne
    @JoinColumn(name = "object_id")
    private Objects objects;

    public Projects() {
    }

    public Projects(String project, Objects objects) {
        this.project = project;
        this.objects = objects;
    }
}
