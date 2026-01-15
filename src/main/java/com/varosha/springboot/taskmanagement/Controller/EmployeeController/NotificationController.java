package com.varosha.springboot.taskmanagement.Controller.EmployeeController;

import com.varosha.springboot.taskmanagement.DTO.notification.NotificationResponseDTO;
import com.varosha.springboot.taskmanagement.Services.NotificationServices;
import com.varosha.springboot.taskmanagement.taskCommon.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ApiResponse<List<NotificationResponseDTO>> getMyUnreadNotifications(@AuthenticationPrincipal String email){
        return ApiResponse.success(200, "OK", notificationServices.getUnreadNotifications(email));
    }

    @PatchMapping()
    public ApiResponse<String> markAllAsRead(@AuthenticationPrincipal String email){
        return ApiResponse.success(200, "OK", notificationServices.markAllAsRead(email));
    }

    @PatchMapping("/{id}/read")
    public ApiResponse<NotificationResponseDTO> markAsRead(@PathVariable Long id) {
        // Return the result of the service call
        NotificationResponseDTO updated = notificationServices.markAsRead(id);
        return ApiResponse.success(200, "Notification marked as read", updated);
    }
}