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


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objects",fetch = FetchType.EAGER)

    private List<Photo> photo;



    public Objects() {
    }

    public Objects(String nameOfObject, Users users, List<Photo> photo) {
        this.nameOfObject = nameOfObject;
        this.users = users;
        this.photo = photo;
    }
}
