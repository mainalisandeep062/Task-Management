package com.varosha.springboot.taskmanagement.Controller;

import com.varosha.springboot.taskmanagement.DTO.auth.LogInRequestDTO;
import com.varosha.springboot.taskmanagement.DTO.auth.LogInResponseDTO;
import com.varosha.springboot.taskmanagement.Services.AuthServices;
import com.varosha.springboot.taskmanagement.taskCommon.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServices authService;

    @PostMapping("/login")
    public ApiResponse<LogInResponseDTO> login(
            @Valid @RequestBody LogInRequestDTO loginRequest) {
        LogInResponseDTO response = authService.login(loginRequest);
        return ApiResponse.success(200, "OK", response);
    }
}
