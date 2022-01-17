package ru.buildservice.project.entity;


import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private int role_id;
    @Column(name="role_name")
    private String roleName;





    public Roles() {
    }

    public Roles(String roleName) {
        this.roleName = roleName;
    }
}
