package com.varosha.springboot.taskmanagement.DTO.task;

public interface OverdueTaskProjection {
    Long getTaskId();
    String getTitle();
    Long getAssigneeId();
}

