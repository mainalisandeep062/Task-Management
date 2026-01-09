package com.varosha.springboot.taskmanagement.DTO.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LogInRequestDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
