package com.varosha.springboot.taskmanagement.Services;

import com.varosha.springboot.taskmanagement.DTO.task.CreateTaskDTO;
import com.varosha.springboot.taskmanagement.DTO.task.TaskResponseDTO;
import com.varosha.springboot.taskmanagement.Enums.TaskStatus;

import java.util.List;

public interface TaskServices {
    TaskResponseDTO createTask(CreateTaskDTO createTaskDTO);
    List<TaskResponseDTO> getAllTask();

    TaskResponseDTO findByName(String name);

    TaskResponseDTO findByStatus(TaskStatus status);

    TaskResponseDTO updateTask(CreateTaskDTO taskDTO);
}
