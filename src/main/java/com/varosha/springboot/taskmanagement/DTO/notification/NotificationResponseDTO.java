package com.varosha.springboot.taskmanagement.DTO.notification;

import com.varosha.springboot.taskmanagement.Enums.NotificationType;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class NotificationResponseDTO {
    private Long id;
    private String title;
    private String message;
    private NotificationType type;
    private String recipientEmail;
    private boolean isRead;
    private LocalDateTime createdAt;
}