package com.varosha.springboot.taskmanagement.Services;

import com.varosha.springboot.taskmanagement.DTO.taskComment.TaskCommentRequestDTO;
import com.varosha.springboot.taskmanagement.DTO.taskComment.TaskCommentResponseDTO;

import java.util.List;

public interface TaskCommentServices {
    TaskCommentResponseDTO addCommentToTask(TaskCommentRequestDTO commentRequestDTO);
    List<TaskCommentResponseDTO> getCommentsByTaskId(Long taskId);
}
