package com.varosha.springboot.taskmanagement.Services;

import com.varosha.springboot.taskmanagement.DTO.task.TaskCommentRequestDTO;
import com.varosha.springboot.taskmanagement.DTO.task.TaskCommentResponseDTO;

import java.util.List;

public interface TaskCommentServices {
    TaskCommentResponseDTO addCommentToTask(TaskCommentRequestDTO commentRequestDTO);
    List<TaskCommentResponseDTO> getCommentsByTaskId(Long taskId);
}
