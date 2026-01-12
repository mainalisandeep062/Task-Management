package com.varosha.springboot.taskmanagement.ServicesImpl;

import com.varosha.springboot.taskmanagement.DTO.task.TaskResponseDTO;
import com.varosha.springboot.taskmanagement.DTO.user.UserResponseDTO;
import com.varosha.springboot.taskmanagement.Repository.UserRepo;
import com.varosha.springboot.taskmanagement.Services.UserServices;
import com.varosha.springboot.taskmanagement.converter.TaskConverter;
import com.varosha.springboot.taskmanagement.converter.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.varosha.springboot.taskmanagement.Configuration.UserDetailsExtractions.*;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices {
    private final UserRepo userRepo;
    private final UserConverter userConverter;
    private final TaskConverter taskConverter;

    @Override
    public UserResponseDTO getCurrentUserProfile() {
        String userEmail = getUserEmail();
        return userRepo.findByEmail(userEmail)
                .map(userConverter::toUserResponseDTO)
                .orElseThrow(() -> new RuntimeException("User not found with email : " + userEmail));
    }

    @Override
public List<TaskResponseDTO> getUserTasks() {
        String userEmail = getUserEmail();
        List<TaskResponseDTO> tasks = userRepo.findByEmail(userEmail)
                .map(user -> user.getTasks()
                        .stream()
                        .map(taskConverter::toTaskResponseDTO)
                        .toList())
                .orElseThrow(() -> new RuntimeException("User not found with email : " + userEmail));
        return tasks;
    }
}
