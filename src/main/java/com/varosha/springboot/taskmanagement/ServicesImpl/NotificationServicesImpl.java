package com.varosha.springboot.taskmanagement.ServicesImpl;

import com.varosha.springboot.taskmanagement.Configuration.ExtractEmail;
import com.varosha.springboot.taskmanagement.DTO.notification.NotificationRequestDTO;
import com.varosha.springboot.taskmanagement.DTO.notification.NotificationResponseDTO;
import com.varosha.springboot.taskmanagement.Models.Notification;
import com.varosha.springboot.taskmanagement.Models.User;
import com.varosha.springboot.taskmanagement.Repository.NotificationRepo;
import com.varosha.springboot.taskmanagement.Repository.UserRepo;
import com.varosha.springboot.taskmanagement.Services.NotificationServices;
import com.varosha.springboot.taskmanagement.converter.NotificationConverter;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServicesImpl implements NotificationServices {
    private final NotificationRepo notificationRepo;
    private final NotificationConverter converter;
    private final UserRepo userRepo;

    @Override
    public NotificationResponseDTO send(NotificationRequestDTO requestDto) {
        Notification notification = converter.toEntity(requestDto);
        if (userRepo.findByUserId(requestDto.getRecipientId()).isEmpty()) {
            return null;
        }
        notification.setCreatedAt(LocalDateTime.now());
        return converter.toDto(notificationRepo.save(notification));
    }

    @Override
    public List<NotificationResponseDTO> getMyNotifications() {
        ExtractEmail email = new ExtractEmail();
        User currentUser = userRepo.findByEmail(email.getEmail()).orElse(null);
        Long recipientId = currentUser.getId();

        return notificationRepo.findByRecipientId(recipientId).stream().map(converter::toDto).collect(Collectors.toList());
    }

    @Override
    public String markAsRead(Long notificationId) {
        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        notificationRepo.save(notification);

        return "Message marked as Read!!!";
    }

    @Override
    public long getUnreadCount() {
        ExtractEmail email = new ExtractEmail();
        User currentUser = userRepo.findByEmail(email.getEmail()).orElse(null);
        Long recipientId = currentUser.getId();
        return notificationRepo.countUnreadByRecipientId(recipientId);
    }


}
