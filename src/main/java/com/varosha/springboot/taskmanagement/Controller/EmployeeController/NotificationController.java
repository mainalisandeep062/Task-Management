package com.varosha.springboot.taskmanagement.Controller.EmployeeController;

import com.varosha.springboot.taskmanagement.Services.NotificationServices;
import com.varosha.springboot.taskmanagement.taskCommon.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationServices notificationServices;

    @GetMapping("/my-notification")
    public ApiResponse getMyNotifications() {
        return ApiResponse.success(200, "OK", notificationServices.getMyNotifications());
    }

    @GetMapping("/unread-count")
    public ApiResponse getUnreadCount() {
        return ApiResponse.success(200, "OK", notificationServices.getUnreadCount());
    }

    @PatchMapping("/{id}/read")
    public ApiResponse markAsRead(@PathVariable Long id) {
        notificationServices.markAsRead(id);
        return ApiResponse.success(200, "Notification marked as read", null);
    }
}