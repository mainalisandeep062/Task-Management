package com.varosha.springboot.taskmanagement.Repository;

import com.varosha.springboot.taskmanagement.Enums.TaskStatus;
import com.varosha.springboot.taskmanagement.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepo extends JpaRepository<Task,Long>{
    Optional<Task> findByTitle(String title);
    Optional<Task> findByStatus(TaskStatus status);
    Optional<Task> findByAssigneeEmail(String assigneeEmail);
    boolean existsByIdAndAssignee_Id(Long taskId, Long userId);

}
