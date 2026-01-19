package com.varosha.springboot.taskmanagement.Repository;

import com.varosha.springboot.taskmanagement.Enums.TaskStatus;
import com.varosha.springboot.taskmanagement.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepo extends JpaRepository<Task,Long>{
    Optional<Task> findByTitle(String title);
    List<Task> findByStatus(TaskStatus status);
    Optional<Task> findByAssigneeId(Long id);
    boolean existsByIdAndAssignee_Id(Long taskId, Long userId);

    @Query(
           value = """
            SELECT * from task t 
            WHERE t.status != 'DONE'
            AND due_date <= CURRENT_DATE
           """ , nativeQuery = true)
    List<Task> findOverdueTasks();

}
