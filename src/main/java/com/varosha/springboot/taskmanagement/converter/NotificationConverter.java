package com.varosha.springboot.taskmanagement.converter;

import com.varosha.springboot.taskmanagement.DTO.notification.NotificationRequestDTO;
import com.varosha.springboot.taskmanagement.DTO.notification.NotificationResponseDTO;
import com.varosha.springboot.taskmanagement.Models.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationConverter {
    public Notification toEntity(NotificationRequestDTO requestDto){
        if(requestDto == null){
            return null;
        }
        Notification notification = new Notification();
        notification.setTitle(requestDto.getTitle());
        notification.setMessage(requestDto.getMessage());
        notification.setType(requestDto.getType());
        notification.setRead(false);

        return notification;
    }

    public NotificationResponseDTO toDto (Notification notification){
        if(notification == null){
            return null;
        }
        NotificationResponseDTO responseDto = new NotificationResponseDTO();
        responseDto.setId(notification.getId());
        responseDto.setTitle(notification.getTitle());
        responseDto.setMessage(notification.getMessage());
        responseDto.setType(notification.getType());
        responseDto.setRead(notification.isRead());
        responseDto.setCreatedAt(notification.getCreatedAt());

        return responseDto;
    }
}
