package com.varosha.springboot.taskmanagement.Repository;

import com.varosha.springboot.taskmanagement.DTO.task.OverdueTaskProjection;
import com.varosha.springboot.taskmanagement.Enums.TaskStatus;
import com.varosha.springboot.taskmanagement.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepo extends JpaRepository<Task, Long> {
    Optional<Task> findByTitle(String title);

    List<Task> findByStatus(TaskStatus status);

    Optional<Task> findByAssigneeId(Long id);

    boolean existsByIdAndAssignee_Id(Long taskId, Long userId);

    @Query("""
        SELECT t.id AS taskId, t.assignee.id AS assigneeId, t.title AS title
        FROM Task t
        WHERE t.dueDate < CURRENT_TIMESTAMP
          AND (t.notified IS NULL OR t.notified = false)
    """)
    List<OverdueTaskProjection> findOverdueTasksNotNotified();

    @Modifying
    @Query("UPDATE Task t SET t.notified = true WHERE t.id IN :taskIds")
    void markAsNotified(@Param("taskIds") List<Long> taskIds);



}
