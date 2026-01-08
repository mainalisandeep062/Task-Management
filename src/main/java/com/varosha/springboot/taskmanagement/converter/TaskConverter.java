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

        if(createTaskDTO.getAssigneeId()!=null)
            userRepo.findById(createTaskDTO.
                    getAssigneeId()).
                    ifPresent(user -> {});

        if(createTaskDTO.getCreatedById()!=null)
            userRepo.findById(createTaskDTO.getCreatedById()).
                    ifPresent(user -> {});


        task.setCreatedAt(createTaskDTO.getCreatedAt());
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
        taskResponseDTO.setCreatedAt(task.getCreatedAt());
        taskResponseDTO.setUpdatedAt(task.getUpdatedAt());

        return  taskResponseDTO;
    }

}