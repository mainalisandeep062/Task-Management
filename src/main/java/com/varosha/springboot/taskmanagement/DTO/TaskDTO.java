package com.varosha.springboot.taskmanagement.DTO;

import com.varosha.springboot.taskmanagement.Enums.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TaskDTO {
    private String title;
    private String description;
    private Date dueDate;
    private TaskStatus status;
    private UserDTO assignee;
    private UserDTO createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
