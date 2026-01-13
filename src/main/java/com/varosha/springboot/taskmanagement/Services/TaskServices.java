package com.varosha.springboot.taskmanagement.Services;

import com.varosha.springboot.taskmanagement.DTO.task.CreateTaskDTO;
import com.varosha.springboot.taskmanagement.DTO.task.TaskResponseDTO;
import com.varosha.springboot.taskmanagement.Enums.TaskStatus;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskServices {
    TaskResponseDTO createTask(CreateTaskDTO createTaskDTO);
    List<TaskResponseDTO> getAllTask();

    TaskResponseDTO findByName(String name);

    @Query("Select t from Task t where t.status = ?1")
    TaskResponseDTO findByStatus(TaskStatus status);

    List<TaskResponseDTO> getTaskByAssigneeEmail(String assigneeEmail);
}
