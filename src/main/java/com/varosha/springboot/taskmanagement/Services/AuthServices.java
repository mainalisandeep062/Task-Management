package com.varosha.springboot.taskmanagement.Services;

import com.varosha.springboot.taskmanagement.DTO.auth.LogInRequestDTO;
import com.varosha.springboot.taskmanagement.DTO.auth.LogInResponseDTO;

public interface AuthServices {

    LogInResponseDTO login(LogInRequestDTO logInRequestDTO);
}
