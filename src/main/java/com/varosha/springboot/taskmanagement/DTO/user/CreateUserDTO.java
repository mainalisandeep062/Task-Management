package com.varosha.springboot.taskmanagement.DTO.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.varosha.springboot.taskmanagement.Enums.Role;
import lombok.Data;

@Data
public class CreateUserDTO {
    @JsonProperty("eMail")
    private String eMail;
    private String password;
    private String fullName;
    private Role role;
}
