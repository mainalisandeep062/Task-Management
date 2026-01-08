package com.varosha.springboot.taskmanagement.DTO.user;

import com.varosha.springboot.taskmanagement.Enums.Role;
import com.varosha.springboot.taskmanagement.Enums.UserStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDTO {
    public Long userId;
    public String fullName;
    public String email;
    public Role role;
    public UserStatus active;
    private LocalDateTime createdDate;
}
