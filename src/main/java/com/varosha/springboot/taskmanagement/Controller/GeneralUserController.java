package com.varosha.springboot.taskmanagement.Controller;

import com.varosha.springboot.taskmanagement.Services.UserServices;
import com.varosha.springboot.taskmanagement.taskCommon.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class GeneralUserController {

    private final UserServices userServices;
    public GeneralUserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/my-profile")
    public ApiResponse getUserProfile() {
        return ApiResponse.success(200, "OK", userServices.getCurrentUserProfile());
    }

    @GetMapping("/my-tasks")
    public ApiResponse getUserTasks() {
        return ApiResponse.success(200, "OK", userServices.getUserTasks());
    }
}
