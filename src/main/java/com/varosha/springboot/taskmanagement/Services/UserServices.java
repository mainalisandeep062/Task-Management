package com.varosha.springboot.taskmanagement.Services;

import com.varosha.springboot.taskmanagement.DTO.task.TaskResponseDTO;
import com.varosha.springboot.taskmanagement.DTO.user.UserResponseDTO;

import java.util.List;

public interface UserServices {
    UserResponseDTO getCurrentUserProfile();
    List<TaskResponseDTO> getUserTasks();
}
