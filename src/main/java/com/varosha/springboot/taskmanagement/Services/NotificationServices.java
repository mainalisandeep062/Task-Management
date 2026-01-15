package com.varosha.springboot.taskmanagement.Services;

import com.varosha.springboot.taskmanagement.DTO.notification.NotificationRequestDTO;
import com.varosha.springboot.taskmanagement.DTO.notification.NotificationResponseDTO;



import java.util.List;

public interface NotificationServices {
    NotificationResponseDTO send(NotificationRequestDTO requestDto);

    List<NotificationResponseDTO> getMyNotifications();

    NotificationResponseDTO markAsRead(Long notificationId);

    List<NotificationResponseDTO> getUnreadNotifications(String email);

    String markAllAsRead(String email);

    long getUnreadCount();
}