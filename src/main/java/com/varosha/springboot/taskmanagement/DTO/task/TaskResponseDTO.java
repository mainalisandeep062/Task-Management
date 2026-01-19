package com.varosha.springboot.taskmanagement.DTO.task;
import com.varosha.springboot.taskmanagement.Enums.TaskStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class TaskResponseDTO {
    private Long taskId;
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskStatus taskStatus;
    private Long assigneeId;
    private String assigneeName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

