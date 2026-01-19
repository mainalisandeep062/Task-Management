package com.varosha.springboot.taskmanagement.DTO.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LogInRequestDTO {

    @NotBlank
    @Email
    @Schema(example = "varosa@example.com", description = "An existing user email.")
    private String email;

    @NotBlank
    private String password;
}
