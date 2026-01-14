package com.varosha.springboot.taskmanagement.Models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "standup")
public class StandUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String accomplishedYesterday;

    @Column(nullable = false, length = 1000)
    private String planForToday;

    @Column(length = 1000)
    private String blockers;

    private LocalDate submissionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}