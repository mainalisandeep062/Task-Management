package com.varosha.springboot.taskmanagement.ServicesImpl;

import com.varosha.springboot.taskmanagement.DTO.task.TaskResponseDTO;
import com.varosha.springboot.taskmanagement.DTO.user.UserResponseDTO;
import com.varosha.springboot.taskmanagement.Enums.TaskStatus;
import com.varosha.springboot.taskmanagement.Models.Task;
import com.varosha.springboot.taskmanagement.Repository.TaskRepo;
import com.varosha.springboot.taskmanagement.Repository.UserRepo;
import com.varosha.springboot.taskmanagement.Services.UserServices;
import com.varosha.springboot.taskmanagement.converter.TaskConverter;
import com.varosha.springboot.taskmanagement.converter.UserConverter;
import lombok.RequiredArgsConstructor;
import com.varosha.springboot.taskmanagement.Configuration.ExtractEmail;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices {
    private final UserRepo userRepo;
    private final UserConverter userConverter;
    private final TaskConverter taskConverter;
    private final ExtractEmail extract;
    private final TaskRepo taskRepo;

    @Override
    public UserResponseDTO getCurrentUserProfile() {
        return userRepo.findByEmail(extract.getEmail())
                .map(userConverter::toUserResponseDTO)
                .orElseThrow(() -> new RuntimeException("User not found with email : " + extract.getEmail()));
    }

    @Override
    public List<TaskResponseDTO> getUserTasks() {
        List<TaskResponseDTO> tasks = userRepo.findByEmail(extract.getEmail())
                .map(user -> user.getTasks()
                        .stream()
                        .map(taskConverter::toTaskResponseDTO)
                        .toList())
                .orElseThrow(() -> new RuntimeException("User not found with email : " + extract.getEmail()));
        return tasks;
    }

    @Override
    public TaskResponseDTO updateTaskStatusById(Long taskId, TaskStatus status) {
        Task task = userRepo.findByEmail(
                extract.getEmail()).orElse(null)
                .getTasks().stream().filter(t -> t.getId().equals(taskId))
                .findFirst().orElseThrow(() -> new RuntimeException("Task not found!!"));
        task.setStatus(status);
        return taskConverter.toTaskResponseDTO(task);
    }

}
