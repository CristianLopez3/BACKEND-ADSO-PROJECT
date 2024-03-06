package com.example.users.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Data
@Entity
@Table(name = "tbl_users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @Column(name = "name", nullable = false, length = 250)

    private String name;

    @Column(name = "lastName", nullable = false, length = 250)

    private String lastName;

    @Column(name = "identification", nullable = false, length = 250, unique = true)

    private String identification;

    @Column(name = "cellphone", nullable = false, unique = true)

    private Long cellphone;

    @Column(name = "email", nullable = false, unique = true)

    private String email;

    @Column(name = "password", nullable = false)

    private String password;

    @Column(name = "role", nullable = false, length = 250)

    private Roles role;


}
