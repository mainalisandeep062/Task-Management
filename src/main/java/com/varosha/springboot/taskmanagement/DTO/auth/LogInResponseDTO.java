package com.varosha.springboot.taskmanagement.DTO.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogInResponseDTO {
    private String token;
    private String role;
    private String email;
}

