package com.varosha.springboot.taskmanagement.taskCommon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private int status;
    private String message;
    private OffsetDateTime timestamp;
    private Object body;

    public static ApiResponse success(int status, String message, Object body) {
        return new ApiResponse(status, message, OffsetDateTime.now(), body);
    }

    public static ApiResponse error(int status, String message, Object body) {
        return new ApiResponse(status, message, OffsetDateTime.now(), body);
    }
}
