package com.varosha.springboot.taskmanagement.DTO.notification;


import com.varosha.springboot.taskmanagement.Enums.NotificationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequestDTO {
    @Schema(example = "1")
    private Long recipientId;
    @Schema(example = "Task assigned!!")
    private String title;
    @Schema(example = "You have been assigned a new task")
    private String message;
    @Schema(allowableValues = {"TASK_ASSIGNED", "STANDUP_REMAINDER", "TASK_DUE_APPROACHING", "TASK_OVERDUE"})
    private NotificationType type;
}
