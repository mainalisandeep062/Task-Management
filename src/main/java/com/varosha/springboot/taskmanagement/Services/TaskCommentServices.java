package com.varosha.springboot.taskmanagement.Services;

import com.varosha.springboot.taskmanagement.DTO.task.TaskCommentResponseDTO;

public interface TaskCommentServices {
    TaskCommentResponseDTO addCommentToTask(Long taskId, Long userId, String commentContent);
}
