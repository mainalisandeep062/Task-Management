package com.varosha.springboot.taskmanagement.Services;

import com.varosha.springboot.taskmanagement.DTO.user.CreateUserDTO;
import com.varosha.springboot.taskmanagement.DTO.user.UserResponseDTO;

import java.util.List;

public interface UserServices {
    UserResponseDTO createUser(CreateUserDTO createUserDTO);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO GetUserByFullName(String fullName);
    UserResponseDTO GetUserByEmail(String email);
}
