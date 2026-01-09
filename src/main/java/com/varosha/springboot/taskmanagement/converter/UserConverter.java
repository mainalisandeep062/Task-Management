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
        user.setActive(createUserDTO.getActive());
        user.setCreatedBy(createUserDTO.getCreatedBy());
        user.setCreatedAt(createUserDTO.getCreatedAt());
        user.setUpdatedBy(createUserDTO.getUpdatedBy());
        user.setUpdatedAt(createUserDTO.getUpdatedAt());
        return user;
    }

    public CreateUserDTO toCreateUserDTO(User user) {
        if(user == null) return null;
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setFullName(user.getFullName());
        createUserDTO.setEMail(user.getEmail());
        createUserDTO.setRole(user.getRole());
        createUserDTO.setActive(user.getActive());
        createUserDTO.setCreatedBy(user.getCreatedBy());
        createUserDTO.setCreatedAt(user.getCreatedAt());
        createUserDTO.setUpdatedBy(user.getUpdatedBy());
        createUserDTO.setUpdatedAt(user.getUpdatedAt());
        return createUserDTO;
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