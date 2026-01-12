package com.varosha.springboot.taskmanagement.ServicesImpl;

import com.varosha.springboot.taskmanagement.DTO.task.CreateTaskDTO;
import com.varosha.springboot.taskmanagement.DTO.task.TaskResponseDTO;
import com.varosha.springboot.taskmanagement.Enums.TaskStatus;
import com.varosha.springboot.taskmanagement.Models.Task;
import com.varosha.springboot.taskmanagement.Repository.TaskRepo;
import com.varosha.springboot.taskmanagement.Repository.UserRepo;
import com.varosha.springboot.taskmanagement.Configuration.ExtractEmail;
import com.varosha.springboot.taskmanagement.Services.TaskServices;
import com.varosha.springboot.taskmanagement.converter.TaskConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServicesImpl implements TaskServices {
    private final TaskRepo taskRepo;
    private final TaskConverter taskConverter;
    private final UserRepo userRepo;
    private ExtractEmail extract;

    @Override
    public TaskResponseDTO createTask(CreateTaskDTO createTaskDTO) {
        Task task = taskConverter.toEntity(createTaskDTO);
        task.setStatus(TaskStatus.TODO);
        task.setCreatedBy(userRepo.findByEmail(extract.getEmail()).
                orElseThrow(() -> new RuntimeException("User not found with email: " + extract.getEmail())));
        task.setCreatedAt(LocalDateTime.now());
        Task createdTask = taskRepo.save(task);
        return taskConverter.toTaskResponseDTO(createdTask);
    }

    @Override
    public List<TaskResponseDTO> getAllTask() {
        return taskRepo.findAll()
                .stream()
                .map(taskConverter::toTaskResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDTO findByName(String title) {
        return taskRepo.findByTitle(title)
                .map(taskConverter::toTaskResponseDTO)
                .orElseThrow(() -> new RuntimeException("Task not found with Title : " + title));
    }

    @Override
    public TaskResponseDTO findByStatus(TaskStatus status) {
        return taskRepo.findByStatus(status)
                .map(taskConverter::toTaskResponseDTO)
                .orElseThrow(() -> new RuntimeException("No Task is " + status.toString() + " right now!!!"));
    }

    @Override
    public TaskResponseDTO getTaskByAssigneeEmail(String assigneeEmail) {
        return taskRepo.findByAssigneeEmail(assigneeEmail)
                .map(taskConverter::toTaskResponseDTO)
                .orElseThrow(() -> new RuntimeException("No Task assigned to user with ID : " + assigneeEmail));
    }


}
