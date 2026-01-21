package com.varosha.springboot.taskmanagement.ServicesImpl;

import com.varosha.springboot.taskmanagement.Configuration.ExtractEmail;
import com.varosha.springboot.taskmanagement.DTO.notification.NotificationRequestDTO;
import com.varosha.springboot.taskmanagement.DTO.notification.NotificationResponseDTO;
import com.varosha.springboot.taskmanagement.DTO.task.OverdueTaskProjection;
import com.varosha.springboot.taskmanagement.Enums.NotificationType;
import com.varosha.springboot.taskmanagement.Models.Notification;
import com.varosha.springboot.taskmanagement.Models.User;
import com.varosha.springboot.taskmanagement.Repository.NotificationRepo;
import com.varosha.springboot.taskmanagement.Repository.TaskRepo;
import com.varosha.springboot.taskmanagement.Repository.UserRepo;
import com.varosha.springboot.taskmanagement.Services.NotificationServices;
import com.varosha.springboot.taskmanagement.converter.NotificationConverter;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServicesImpl implements NotificationServices {
    private final NotificationRepo notificationRepo;
    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationConverter converter;
    private final UserRepo userRepo;
    private final TaskRepo taskRepo;

    @Override
    public NotificationResponseDTO send(NotificationRequestDTO requestDto) {
        // Fetch the user once. If not found, throw an exception or return null.
        User recipient = userRepo.findById(requestDto.getRecipientId())
                .orElseThrow(() -> new RuntimeException("Recipient not found with ID: " + requestDto.getRecipientId()));

        Notification notification = converter.toEntity(requestDto);
        notification.setRecipient(recipient);
        notification.setRecipientEmail(recipient.getEmail());
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRead(false);

        Notification savedNotification = notificationRepo.save(notification);
        NotificationResponseDTO responseDto = converter.toDto(savedNotification);

        // Push Real-time via WebSocket
        messagingTemplate.convertAndSendToUser(recipient.getEmail(),
                "/queue/notifications",
                responseDto // Send the DTO, it's cleaner for the frontend
        );

        return responseDto;
    }

    @Override
    public List<NotificationResponseDTO> getMyNotifications() {
        ExtractEmail email = new ExtractEmail();
        return notificationRepo.findByRecipientEmail(email.getEmail())
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationResponseDTO> getUnreadNotifications(String email) {
        return notificationRepo.getUnreadNotifications(email)
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String markAllAsRead(String email) {
        int updated = notificationRepo.markAllAsReadByRecipientEmail(email);
        if(updated == 0){
            return "Notifications were already marked as read";
        }
        return "Marked all as read successfully";
    }

    @Override
    public String markAsRead(Long notificationId) {
        int updated = notificationRepo.markAsReadByNotificationId(notificationId);

        String userEmail =  notificationRepo.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found!!!"))
                .getRecipientEmail();
        if(updated == 1){
            messagingTemplate.convertAndSendToUser(
                    userEmail,
                    "/queue/notifications/read-updates",
                    "Marked as read!!");
        }
        return "Notification marked as read!!";
    }

    @Override
    public long getUnreadCount() {
        String recipientEmail = new ExtractEmail().getEmail();
        return notificationRepo.countUnreadByRecipientEmail(recipientEmail);
    }

    @Transactional
    @Scheduled(cron = "0 0 18 * * *")
    public void overDueTaskNotification() {
        List<OverdueTaskProjection> overDueTasks = taskRepo.findOverdueTasksNotNotified();

        for (OverdueTaskProjection task : overDueTasks) {
                send(NotificationRequestDTO.builder()
                        .recipientId(task.getAssigneeId())
                        .title("⚠️Task Overdue Alert!!!")
                        .message("\"" + task.getTitle() + "\" is OverDue!!!")
                        .type(NotificationType.TASK_OVERDUE)
                        .referenceId(task.getTaskId())
                        .build());
        }
        taskRepo.markAsNotified(overDueTasks.stream().map(OverdueTaskProjection::getTaskId).toList());
        log.info("Sent overdue notifications for {} tasks", overDueTasks.size());
    }

}
