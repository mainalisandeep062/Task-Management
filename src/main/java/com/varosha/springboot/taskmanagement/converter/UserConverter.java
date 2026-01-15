package com.varosha.springboot.taskmanagement.converter;

import com.varosha.springboot.taskmanagement.DTO.user.CreateUserDTO;
import com.varosha.springboot.taskmanagement.DTO.user.UserResponseDTO;
import com.varosha.springboot.taskmanagement.Models.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User toEntity(CreateUserDTO createUserDTO) {
        if (createUserDTO == null) return null;
        User user = new User();
        user.setFullName(createUserDTO.getFullName());
        user.setEmail(createUserDTO.getEMail());
        user.setPassword(createUserDTO.getPassword());
        user.setRole(createUserDTO.getRole());

        return user;
    }


    public UserResponseDTO toUserResponseDTO(User user) {
        if(user == null) return null;
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserId(user.getId());
        userResponseDTO.setFullName(user.getFullName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setRole(user.getRole());
        userResponseDTO.setActive(user.getActive());

        return  userResponseDTO;
    }
}