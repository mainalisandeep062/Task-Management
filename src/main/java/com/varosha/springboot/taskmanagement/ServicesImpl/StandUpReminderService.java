package com.varosha.springboot.taskmanagement.ServicesImpl;

import com.varosha.springboot.taskmanagement.DTO.notification.NotificationRequestDTO;
import com.varosha.springboot.taskmanagement.Enums.NotificationType;
import com.varosha.springboot.taskmanagement.Models.User;
import com.varosha.springboot.taskmanagement.Repository.StandUpRepo;
import com.varosha.springboot.taskmanagement.Repository.UserRepo;
import com.varosha.springboot.taskmanagement.Services.NotificationServices;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StandUpReminderService {

    private final UserRepo userRepo;
    private final StandUpRepo standUpRepo;
    private final NotificationServices notificationServices;

    // Runs at 2:00 PM every day (Cron: sec min hour day month weekday)
    @Scheduled(cron = "0 0 14 * * *")
    public void sendReminders() {
        List<User> users = userRepo.findAll();
        for (User user : users) {
            boolean submitted = standUpRepo.existsByUserAndSubmissionDate(user, LocalDate.now());
            if (!submitted) {
                notificationServices.send(NotificationRequestDTO.builder()
                        .recipientId(user.getId())
                        .title("Missing Standup Reminder")
                        .message("Please submit what you did yesterday and your plans for today.")
                        .type(NotificationType.STANDUP_REMAINDER)
                        .build());
            }
        }
    }
}