package com.tade.secure_file_api.model;

import java.time.LocalDate;
import jakarta.persistence.*;

/*
* Added a User table with some basic fields
* Used annotations to define entity and columns
*/

@Entity
public interface User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role;
    private LocalDate createdAt;
}
