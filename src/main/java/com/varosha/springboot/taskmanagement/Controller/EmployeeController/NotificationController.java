package com.varosha.springboot.taskmanagement.Controller.EmployeeController;

import com.varosha.springboot.taskmanagement.DTO.notification.NotificationResponseDTO;
import com.varosha.springboot.taskmanagement.Services.NotificationServices;
import com.varosha.springboot.taskmanagement.taskCommon.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
@Tag(name = "Notification Dashboard")
public class NotificationController {

    private final NotificationServices notificationServices;

    @Operation(
            summary = "Fetch User's notifications",
            description = "View all the notifications of current user")
    @GetMapping("/my-notification")
    public ApiResponse<List<NotificationResponseDTO>> getMyNotifications() {
        return ApiResponse.success(200, "OK", notificationServices.getMyNotifications());
    }

    @Operation(
            summary = "Get Number of unread Notifications",
            description = "This is for the Unread count badge for bell icon")
    @GetMapping("/unread-count")
    public ApiResponse<Long> getUnreadCount() {
        return ApiResponse.success(200, "OK", notificationServices.getUnreadCount());
    }

    @Operation(
            summary = "Get all unread Notifications",
            description = "View list of unread notification of current user.")
    @GetMapping("/unread")
    public ApiResponse<List<NotificationResponseDTO>> getMyUnreadNotifications(@AuthenticationPrincipal String email){
        return ApiResponse.success(200, "OK", notificationServices.getUnreadNotifications(email));
    }

    @Operation(
            summary = "Mark all notification as read",
            description = "This marks every uread notification as read")
    @PatchMapping()
    public ApiResponse<String> markAllAsRead(@AuthenticationPrincipal String email){
        return ApiResponse.success(200, "OK", notificationServices.markAllAsRead(email));
    }

    @Operation(
            summary = "Mark as Read",
            description = "This marks certain notification as read at a time.")
    @PatchMapping("/{notificationId}/read")
    public ApiResponse<String> markAsRead(@PathVariable Long notificationId) {
        return ApiResponse.success(200, "Notification marked as read", notificationServices.markAsRead(notificationId));
    }
}