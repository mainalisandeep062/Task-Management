package com.varosha.springboot.taskmanagement.Repository;

import com.varosha.springboot.taskmanagement.DTO.task.TaskResponseDTO;
import com.varosha.springboot.taskmanagement.Models.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskCommentRepo extends JpaRepository<TaskComment, Long> {
    Optional<TaskResponseDTO> findByTaskId(Long taskId);
    Optional<TaskResponseDTO> findByCommentedById(Long userId);
}
