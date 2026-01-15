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
import jakarta.transaction.Transactional;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServicesImpl implements NotificationServices {
    private final NotificationRepo notificationRepo;
    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationConverter converter;
    private final UserRepo userRepo;

    @Override
    public NotificationResponseDTO send(NotificationRequestDTO requestDto) {
        // Fetch the user once. If not found, throw an exception or return null.
        User recipient = userRepo.findById(requestDto.getRecipientId())
                .orElseThrow(() -> new RuntimeException("Recipient not found with ID: " + requestDto.getRecipientId()));

        Notification notification = converter.toEntity(requestDto);
        notification.setRecipient(recipient);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRead(false);

        Notification savedNotification = notificationRepo.save(notification);
        NotificationResponseDTO responseDto = converter.toDto(savedNotification);

        // Push Real-time via WebSocket
        messagingTemplate.convertAndSendToUser(
                recipient.getEmail(),
                "/queue/notifications",
                responseDto // Send the DTO, it's cleaner for the frontend
        );

        return responseDto;
    }

    @Override
    public List<NotificationResponseDTO> getMyNotifications() {
        ExtractEmail email = new ExtractEmail();
        User currentUser = userRepo.findByEmail(email.getEmail()).orElse(null);
        Long recipientId = currentUser.getId();

        return notificationRepo.findByRecipientId(recipientId)
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationResponseDTO> getUnreadNotifications(String email) {
        return notificationRepo.findByRecipientId(userRepo.findByEmail(email).orElse(null).getId())
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String markAllAsRead(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Notification> unreadNotifs = notificationRepo.findByRecipientId(user.getId())
                .stream()
                .filter(n -> !n.isRead())
                .toList();

        if (!unreadNotifs.isEmpty()) {
            unreadNotifs.forEach(n -> n.setRead(true));
            notificationRepo.saveAll(unreadNotifs);

            messagingTemplate.convertAndSendToUser(
                    email,
                    "/queue/notifications/read-updates",
                    "ALL_READ"
            );
        }
        return "All Notifications Marked as read!!";
    }

    @Override
    public NotificationResponseDTO markAsRead(Long notificationId) {
        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        notificationRepo.save(notification);

        messagingTemplate.convertAndSendToUser(
                notification.getRecipient().getEmail(),
                "/queue/notifications/read-updates",
                converter.toDto(notification)
        );

        return converter.toDto(notification);
    }

    @Override
    public long getUnreadCount() {
        ExtractEmail email = new ExtractEmail();
        User currentUser = userRepo.findByEmail(email.getEmail()).orElse(null);
        Long recipientId = currentUser.getId();
        return notificationRepo.countUnreadByRecipientId(recipientId);
    }


}
