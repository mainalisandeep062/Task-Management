package com.varosha.springboot.taskmanagement.Models;

import com.varosha.springboot.taskmanagement.Enums.Role;
import com.varosha.springboot.taskmanagement.Enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "e_mail", unique = true, nullable = false,  length = 50)
    private String email;

    @Column(nullable = false,  length = 255)
    private String password;
    private String fullName;

    @Column(name = "role",  nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus active;

    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;

}
