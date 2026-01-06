package com.varosha.springboot.taskmanagement.converter;

import com.varosha.springboot.taskmanagement.DTO.TaskDTO;
import com.varosha.springboot.taskmanagement.DTO.UserDTO;
import com.varosha.springboot.taskmanagement.Models.Task;
import com.varosha.springboot.taskmanagement.Models.User;

public class TaskConverter {
    public Task toEntity(TaskDTO taskDTO) {
        Task task = new Task();
        if (taskDTO == null) {
            return null;
        }
        User assignee = new User();
        User assigner = new User();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());
        task.setStatus(taskDTO.getStatus());
        if(taskDTO.getAssignee() != null) {
            assignee.setFullName(taskDTO.getAssignee().getFullName());
        }
        task.setAssignee(assignee);
        if(taskDTO.getCreatedBy() != null) {
            assigner.setFullName(taskDTO.getCreatedBy().getFullName());
        }
        task.setCreatedBy(assigner);
        task.setCreatedAt(taskDTO.getCreatedAt());
        task.setUpdatedAt(taskDTO.getUpdatedAt());
        return task;
    }

    public TaskDTO toDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        if (task == null) {
            return null;
        }
        UserDTO assignee = new UserDTO();
        UserDTO assigner = new UserDTO();

        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setDueDate(task.getDueDate());
        taskDTO.setStatus(task.getStatus());
        if(task.getAssignee() != null) {
            assignee.setFullName(task.getAssignee().getFullName());
        }
        taskDTO.setAssignee(assignee);
        if(task.getCreatedBy() != null) {
            assigner.setFullName(task.getCreatedBy().getFullName());
        }
        taskDTO.setCreatedBy(assigner);
        taskDTO.setCreatedAt(task.getCreatedAt());
        taskDTO.setUpdatedAt(task.getUpdatedAt());


        return  taskDTO;
    }
}