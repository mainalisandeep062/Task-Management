package com.varosha.springboot.taskmanagement.DTO.task;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskCommentRequestDTO {
    private String commentContent;
    private Long taskId;
    private Long userId;
    private LocalDateTime commentedAt;

}
