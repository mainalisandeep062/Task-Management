package com.varosha.springboot.taskmanagement.Services;

import com.varosha.springboot.taskmanagement.DTO.user.CreateUserDTO;
import com.varosha.springboot.taskmanagement.DTO.user.UserResponseDTO;

import java.util.List;

public interface AdminServices {
    UserResponseDTO createUser(CreateUserDTO createUserDTO, String createdBy);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserByFullName(String fullName);
    UserResponseDTO getUserByEmail(String email);
    UserResponseDTO deactivateUsrById(Long userId, String updatedBy);
}
