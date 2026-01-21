package com.varosha.springboot.taskmanagement.DTO.taskComment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskCommentResponseDTO {
    private String commentContent;
    private String taskTitle;
    private String commentedName;
    private LocalDateTime commentedAt;
}
