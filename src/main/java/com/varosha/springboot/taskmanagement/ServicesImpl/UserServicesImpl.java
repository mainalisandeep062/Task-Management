package com.varosha.springboot.taskmanagement.ServicesImpl;

import com.varosha.springboot.taskmanagement.DTO.user.CreateUserDTO;
import com.varosha.springboot.taskmanagement.DTO.user.UserResponseDTO;
import com.varosha.springboot.taskmanagement.Models.User;
import com.varosha.springboot.taskmanagement.Repository.UserRepo;
import com.varosha.springboot.taskmanagement.Services.UserServices;
import com.varosha.springboot.taskmanagement.converter.UserConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServicesImpl implements UserServices {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;

    public UserServicesImpl(UserRepo userRepo,
                            PasswordEncoder passwordEncoder,
                            UserConverter userConverter) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.userConverter = userConverter;
    }

    @Override
    public UserResponseDTO createUser(CreateUserDTO createUserDTO) {
        User user = userConverter.toEntity(createUserDTO);
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        User savedUser = userRepo.save(user);
        return userConverter.toUserResponseDTO(savedUser);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(userConverter::toUserResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO GetUserByFullName(String fullName) {
        return userRepo.findByFullNameIgnoreCase(fullName)
                .map(userConverter::toUserResponseDTO)
                .orElseThrow(() -> new RuntimeException("User not found with name : " + fullName));
    }

    @Override
    public UserResponseDTO GetUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .map(userConverter::toUserResponseDTO)
                .orElseThrow(() -> new RuntimeException("User not found with email : " + email));
    }
}