package com.varosha.springboot.taskmanagement.DTO.notification;


import com.varosha.springboot.taskmanagement.Enums.NotificationType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationRequestDTO {
    private Long recipientId;
    private String title;
    private String message;
    private NotificationType type;
}
