package com.varosha.springboot.taskmanagement.Repository;

import com.varosha.springboot.taskmanagement.DTO.task.TaskResponseDTO;
import com.varosha.springboot.taskmanagement.Models.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskCommentRepo extends JpaRepository<TaskComment, Long> {
    @Query("SELECT c FROM TaskComment c WHERE c.task.id = :taskId")
    List<TaskComment> findByTaskId(Long taskId);

    Optional<TaskResponseDTO> findByCommentedById(Long userId);
}
