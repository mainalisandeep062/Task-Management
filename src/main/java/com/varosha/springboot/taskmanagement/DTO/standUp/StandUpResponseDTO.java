package com.varosha.springboot.taskmanagement.DTO.standUp;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StandUpResponseDTO {
    private Long id;
    private String accomplishedYesterday;
    private String planForToday;
    private String blockers;
    private Long userId;
    private LocalDate submittedDate;
}
