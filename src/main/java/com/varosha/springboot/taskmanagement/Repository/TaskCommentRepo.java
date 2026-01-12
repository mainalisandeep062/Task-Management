package com.varosha.springboot.taskmanagement.Repository;

import com.varosha.springboot.taskmanagement.Models.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCommentRepo extends JpaRepository<TaskComment, Long> {

}
