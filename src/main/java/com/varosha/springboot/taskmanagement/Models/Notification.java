package com.varosha.springboot.taskmanagement.Models;

import com.varosha.springboot.taskmanagement.Enums.NotificationType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity

public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private boolean isRead = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", nullable = false)
    private User recipient;

    private LocalDateTime createdAt;

}
