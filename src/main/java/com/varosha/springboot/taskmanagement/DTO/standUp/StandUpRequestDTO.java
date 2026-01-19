package com.varosha.springboot.taskmanagement.DTO.standUp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class StandUpRequestDTO {
    @Schema(example = "Implemented this in that project")
    private String accomplishedYesterday;
    @Schema(example = "Configure that in this project")
    private String planForToday;
    @Schema(example = "Can't test the API because third-party testing API is down")
    private String blockers;
}