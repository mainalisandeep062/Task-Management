package com.varosha.springboot.taskmanagement.DTO.task;

import com.varosha.springboot.taskmanagement.Enums.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CreateTaskDTO {
    private String title;
    private String description;
    private Date dueDate;
    private TaskStatus status;
    private Long assigneeId;
    private Long createdById;
    private LocalDateTime createdAt;
}
