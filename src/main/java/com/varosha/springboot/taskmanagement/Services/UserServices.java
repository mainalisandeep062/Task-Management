package com.varosha.springboot.taskmanagement.Services;

import com.varosha.springboot.taskmanagement.DTO.task.TaskResponseDTO;
import com.varosha.springboot.taskmanagement.DTO.user.UserResponseDTO;
import com.varosha.springboot.taskmanagement.Enums.TaskStatus;

import java.util.List;

public interface UserServices {
    UserResponseDTO getCurrentUserProfile();
    List<TaskResponseDTO> getUserTasks();
    TaskResponseDTO updateTaskStatusById(Long taskId, TaskStatus status);
}
