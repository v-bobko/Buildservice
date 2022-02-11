package ru.buildservice.project.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Users {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int user_id;
    @Getter
    @Setter
    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;



    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles roles;


    @OneToMany(mappedBy ="users",cascade = CascadeType.ALL)
    private List<Objects> objects;

    @OneToMany(mappedBy ="users",cascade = CascadeType.ALL)
    private List<CalendarService> calendarService;

    @OneToMany(mappedBy ="users",cascade = CascadeType.ALL)
    private List<Journal> journals;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public Users() {
    }

    public Users(String username, String password, Roles roles) {
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.roles = roles;
    }




}
