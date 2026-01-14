package com.varosha.springboot.taskmanagement.DTO.notification;


import com.varosha.springboot.taskmanagement.Enums.NotificationType;
import lombok.Data;

@Data
public class NotificationRequestDTO {
    private Long recipientId;
    private String title;
    private String message;
    private NotificationType type;
}
