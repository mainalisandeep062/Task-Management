package com.varosha.springboot.taskmanagement.DTO.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.varosha.springboot.taskmanagement.Enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateUserDTO {
    @JsonProperty("eMail")
    @Schema(example = "varosa@example.com")
    private String eMail;
    @Schema(example = "SystemAdmin")
    private String password;
    @Schema(example = "John Doe")
    private String fullName;
    @Schema(allowableValues = {"ADMIN", "EMPLOYEE"})
    private Role role;
}
