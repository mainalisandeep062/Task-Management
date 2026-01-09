package com.varosha.springboot.taskmanagement.DTO.task;

import com.varosha.springboot.taskmanagement.DTO.user.CreateUserDTO;
import com.varosha.springboot.taskmanagement.Enums.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TaskResponseDTO {
    private Long taskId;
    private String title;
    private String description;
    private Date dueDate;
    private TaskStatus taskStatus;
    private Long assigneeId;
    private String assigneeName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

