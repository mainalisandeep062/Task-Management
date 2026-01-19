package com.varosha.springboot.taskmanagement.DTO.task;

import com.varosha.springboot.taskmanagement.Enums.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;


@Data
public class CreateTaskDTO {
    @Schema(example = "Optimize the newly found bug")
    private String title;
    @Schema(example = "There has been report of a bug in that project. Debug and optimize the application.")
    private String description;
    @Schema(example = "yyyy-mm-dd")
    private LocalDate dueDate;
    @Schema(allowableValues = {"TODO", "IN_PROGRESS", "DONE"})
    private TaskStatus status;
    private Long assigneeId;
}
