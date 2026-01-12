package com.varosha.springboot.taskmanagement.converter;

import com.varosha.springboot.taskmanagement.DTO.task.CreateTaskDTO;
import com.varosha.springboot.taskmanagement.DTO.task.TaskResponseDTO;
import com.varosha.springboot.taskmanagement.Models.Task;
import com.varosha.springboot.taskmanagement.Repository.UserRepo;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter {
    private UserRepo userRepo;
    public  TaskConverter(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Task toEntity(CreateTaskDTO createTaskDTO) {
        Task task = new Task();
        if (createTaskDTO == null) {
            return null;
        }
        task.setTitle(createTaskDTO.getTitle());
        task.setDescription(createTaskDTO.getDescription());
        task.setDueDate(createTaskDTO.getDueDate());
        task.setStatus(createTaskDTO.getStatus());

        if (createTaskDTO.getAssigneeId() != null) {
            userRepo.findById(createTaskDTO.getAssigneeId())
                    .ifPresent(task::setAssignee);
        }

        return task;
    }

    public TaskResponseDTO toTaskResponseDTO(Task task) {
        if(task == null) {
            return  null;
        }
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setTaskId(task.getId());
        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setDescription(task.getDescription());
        taskResponseDTO.setDueDate(task.getDueDate());
        taskResponseDTO.setTaskStatus(task.getStatus());
        if(task.getAssignee() != null) {
            taskResponseDTO.setAssigneeId(task.getAssignee().getId());
        }
        if(task.getAssignee() != null) {
            taskResponseDTO.setAssigneeName(task.getAssignee().getFullName());
        }
        taskResponseDTO.setCreatedAt(task.getCreatedAt());
        taskResponseDTO.setUpdatedAt(task.getUpdatedAt());

        return  taskResponseDTO;
    }

}