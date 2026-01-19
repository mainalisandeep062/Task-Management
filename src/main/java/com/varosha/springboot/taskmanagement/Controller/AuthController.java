package com.varosha.springboot.taskmanagement.Controller;

import com.varosha.springboot.taskmanagement.DTO.auth.LogInRequestDTO;
import com.varosha.springboot.taskmanagement.DTO.auth.LogInResponseDTO;
import com.varosha.springboot.taskmanagement.Services.AuthServices;
import com.varosha.springboot.taskmanagement.taskCommon.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {

    private final AuthServices authService;

    @Operation(summary = "Get JWT token",
               description = "Enter Credentials to receive a token.")
    @SecurityRequirements
    @PostMapping("/login")
    public ApiResponse<LogInResponseDTO> login(
            @Valid @RequestBody LogInRequestDTO loginRequest) {
        LogInResponseDTO response = authService.login(loginRequest);
        return ApiResponse.success(200, "OK", response);
    }
}
