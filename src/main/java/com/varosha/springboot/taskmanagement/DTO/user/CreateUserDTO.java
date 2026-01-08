package com.varosha.springboot.taskmanagement.DTO.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.varosha.springboot.taskmanagement.Enums.Role;
import com.varosha.springboot.taskmanagement.Enums.UserStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateUserDTO {
    @JsonProperty("eMail")
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
