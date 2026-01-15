package com.varosha.springboot.taskmanagement.Controller.EmployeeController;

import com.varosha.springboot.taskmanagement.DTO.notification.NotificationResponseDTO;
import com.varosha.springboot.taskmanagement.Services.NotificationServices;
import com.varosha.springboot.taskmanagement.taskCommon.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationServices notificationServices;

    @GetMapping("/my-notification")
    public ApiResponse<List<NotificationResponseDTO>> getMyNotifications() {
        return ApiResponse.success(200, "OK", notificationServices.getMyNotifications());
    }

    @GetMapping("/unread-count")
    public ApiResponse<Long> getUnreadCount() {
        return ApiResponse.success(200, "OK", notificationServices.getUnreadCount());
    }

    @GetMapping("/unread")
    public ApiResponse<List<NotificationResponseDTO>> getMyUnreadNotifications(){
        return ApiResponse.success(200, "OK", notificationServices.getUnreadNotifications());
    }

    @PatchMapping("/{id}/read")
    public ApiResponse<NotificationResponseDTO> markAsRead(@PathVariable Long id) {
        notificationServices.markAsRead(id);
        return ApiResponse.success(200, "Notification marked as read", null);
    }
}