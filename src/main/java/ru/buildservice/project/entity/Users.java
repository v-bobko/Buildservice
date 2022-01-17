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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Roles roles;

    @OneToOne(mappedBy = "users",cascade = CascadeType.ALL)
    private Objects objects;







    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public Users() {
    }

    public Users(String username, String password, Roles roles, Objects objects) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.objects = objects;
    }
}
