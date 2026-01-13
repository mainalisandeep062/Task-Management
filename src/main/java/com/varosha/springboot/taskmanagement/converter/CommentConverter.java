package com.varosha.springboot.taskmanagement.converter;

import com.varosha.springboot.taskmanagement.DTO.task.TaskCommentResponseDTO;
import com.varosha.springboot.taskmanagement.Models.TaskComment;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {
    public TaskCommentResponseDTO toDTO(TaskComment taskComment) {
        if (taskComment == null) return null;
        TaskCommentResponseDTO commentDTO = new TaskCommentResponseDTO();
        commentDTO.setCommentContent(taskComment.getCommentContent());
        commentDTO.setTaskTitle(taskComment.getTask().getTitle());
        commentDTO.setCommentedName(taskComment.getCommentedBy().getFullName());
        return commentDTO;
    }
}
