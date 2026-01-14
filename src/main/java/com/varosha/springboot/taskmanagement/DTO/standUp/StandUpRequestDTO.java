package com.varosha.springboot.taskmanagement.DTO.standUp;

import lombok.Data;

@Data
public class StandUpRequestDTO {
    private String accomplishedYesterday;
    private String planForToday;
    private String blockers;
}