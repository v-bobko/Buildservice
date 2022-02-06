package ru.buildservice.project.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "objects", uniqueConstraints = @UniqueConstraint(columnNames = "name_of_object"))
public class Objects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "object_id")
    private int objectId;

    @Column(name = "name_of_object")
    private String nameOfObject;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToMany(mappedBy ="objects",cascade = CascadeType.ALL)
    private List<CalendarService> calendarService;

    @OneToMany(mappedBy ="objects",cascade = CascadeType.ALL)
    private List<Journal> journal;

    @OneToMany(mappedBy ="objects",cascade = CascadeType.ALL)
    private List<Photo> photo;

    @OneToMany(mappedBy ="objects",cascade = CascadeType.ALL)
    private List<Projects> project;

    @OneToMany(mappedBy ="objects",cascade = CascadeType.ALL)
    private List<Estimates> estimates;
  
    @OneToMany(mappedBy ="objects",cascade = CascadeType.ALL)
    private List<CalendarComment> calendarComments;




    public Objects() {
    }

    public Objects(String nameOfObject, Users users) {
        this.nameOfObject = nameOfObject;
        this.users = users;

    }


}
