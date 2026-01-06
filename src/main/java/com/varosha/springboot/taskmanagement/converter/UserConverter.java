package com.varosha.springboot.taskmanagement.converter;

import com.varosha.springboot.taskmanagement.DTO.UserDTO;
import com.varosha.springboot.taskmanagement.Models.User;

public class UserConverter {
    public User toEntity(UserDTO userDTO) {
         User user = new User();
         if(user == null){
             return null;
         }

         user.setFullName(userDTO.getFullName());
         user.setEMail(userDTO.getEMail());
         user.setPassword(userDTO.getPassword());
         try{
            user.setRole(userDTO.getRole());
         }catch(IllegalArgumentException e){
             throw new IllegalArgumentException("Invalid role: " + userDTO.getRole() +
                     ". Allowed roles are: Admin, Employee");
         }
         user.setActive(userDTO.getActive());
         user.setCreatedBy(userDTO.getCreatedBy());
         user.setCreatedAt(userDTO.getCreatedAt());
         user.setUpdatedBy(userDTO.getUpdatedBy());
         user.setUpdatedAt(userDTO.getUpdatedAt());
         return user;
    }

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        if(user == null){
            return null;
        }

        userDTO.setFullName(user.getFullName());
        userDTO.setEMail(user.getEMail());
        userDTO.setPassword(user.getPassword());
        try{
            userDTO.setRole(user.getRole());
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid role: " + userDTO.getRole() +
                    ". Allowed roles are: Admin, Employee");
        }
        userDTO.setActive(user.getActive());
        userDTO.setCreatedBy(user.getCreatedBy());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedBy(user.getUpdatedBy());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        return userDTO;
    }
}

