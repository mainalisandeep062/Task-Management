package com.varosha.springboot.taskmanagement.DTO.taskComment;

import lombok.Data;

@Data
public class TaskCommentRequestDTO {
    private String commentContent;
    private Long taskId;
    private Long userId;
}
