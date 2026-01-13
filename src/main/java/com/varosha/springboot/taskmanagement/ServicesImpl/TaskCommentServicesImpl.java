package com.varosha.springboot.taskmanagement.ServicesImpl;

import com.varosha.springboot.taskmanagement.Configuration.ExtractEmail;
import com.varosha.springboot.taskmanagement.DTO.task.TaskCommentRequestDTO;
import com.varosha.springboot.taskmanagement.DTO.task.TaskCommentResponseDTO;
import com.varosha.springboot.taskmanagement.Enums.Role;
import com.varosha.springboot.taskmanagement.Models.Task;
import com.varosha.springboot.taskmanagement.Models.TaskComment;
import com.varosha.springboot.taskmanagement.Models.User;
import com.varosha.springboot.taskmanagement.Repository.TaskCommentRepo;
import com.varosha.springboot.taskmanagement.Repository.TaskRepo;
import com.varosha.springboot.taskmanagement.Repository.UserRepo;
import com.varosha.springboot.taskmanagement.Services.TaskCommentServices;
import com.varosha.springboot.taskmanagement.converter.CommentConverter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskCommentServicesImpl implements TaskCommentServices {
    private final CommentConverter commentConverter;
    private final TaskCommentRepo taskCommentRepo;
    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    public TaskCommentServicesImpl(CommentConverter commentConverter,
                                   TaskCommentRepo taskCommentRepo,
                                    TaskRepo taskRepo, UserRepo userRepo) {
        this.commentConverter = commentConverter;
        this.taskCommentRepo = taskCommentRepo;
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    @Override
    public TaskCommentResponseDTO addCommentToTask(TaskCommentRequestDTO commentRequestDTO) {
        String email = new ExtractEmail().getEmail();
        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        Task task = taskRepo.findById(commentRequestDTO.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));

// TODO: this is to check if the user is ADMIN or not
        if (!user.getRole().equals(Role.ADMIN)) {
    //TODo: This is to check if the user is adding comment to his assigned task
            if (!taskRepo.existsByIdAndAssignee_Id(task.getId(), user.getId()))
                throw new RuntimeException("User is not assigned to this task");
        }

        TaskComment taskComment = new TaskComment();
        taskComment.setCommentContent(commentRequestDTO.getCommentContent());
        taskComment.setTask(taskRepo.findById(commentRequestDTO
                .getTaskId()).orElse(null));
        taskComment.setCommentedBy(userRepo.findByEmail(email).orElse(null));
        taskComment.setCommentedAt(LocalDateTime.now());
        TaskComment savedComment = taskCommentRepo.save(taskComment);
        return commentConverter.toDTO(savedComment);
    }

    @Override
    public List<TaskCommentResponseDTO> getCommentsByTaskId(Long taskId) {
       List<TaskComment> comments = (taskCommentRepo.findByTaskId(taskId));

        return comments.stream()
                .map(commentConverter::toDTO)
                .collect(Collectors.toList());
    }


}
