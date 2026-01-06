package com.varosha.springboot.taskmanagement.DTO;

import com.varosha.springboot.taskmanagement.Enums.Role;
import com.varosha.springboot.taskmanagement.Enums.UserStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private String eMail;
    private String password;
    private String fullName;
    private Role role;
    private UserStatus active;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;

}
