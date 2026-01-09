package com.varosha.springboot.taskmanagement.ServicesImpl;

import com.varosha.springboot.taskmanagement.Configuration.JwtConfig;
import com.varosha.springboot.taskmanagement.DTO.auth.LogInRequestDTO;
import com.varosha.springboot.taskmanagement.DTO.auth.LogInResponseDTO;
import com.varosha.springboot.taskmanagement.Models.User;
import com.varosha.springboot.taskmanagement.Repository.UserRepo;
import com.varosha.springboot.taskmanagement.Services.AuthServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServicesImpl implements AuthServices {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;

    @Override
    public LogInResponseDTO login(LogInRequestDTO logInRequestDTO) {
        User user = userRepo.findByEmail(logInRequestDTO.getEmail())
                .orElseThrow(() -> new BadCredentialsException("User not found"));

        if(!passwordEncoder.matches(logInRequestDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        String role = user.getRole().name();

        String token = jwtConfig.generateToken(user.getEmail(), role);

        return new LogInResponseDTO(token, role, user.getEmail());
    }
}
