package com.varosha.springboot.taskmanagement.Controller.EmployeeController;

import com.varosha.springboot.taskmanagement.DTO.task.TaskCommentRequestDTO;
import com.varosha.springboot.taskmanagement.DTO.task.TaskCommentResponseDTO;
import com.varosha.springboot.taskmanagement.Services.TaskCommentServices;
import com.varosha.springboot.taskmanagement.Services.UserServices;
import com.varosha.springboot.taskmanagement.taskCommon.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class EmployeeController {

    private final UserServices userServices;
    private final TaskCommentServices commentServices;
    public EmployeeController(UserServices userServices, TaskCommentServices commentServices) {
        this.userServices = userServices;
        this.commentServices = commentServices;
    }

    @GetMapping("/my-profile")
    public ApiResponse getUserProfile() {
        return ApiResponse.success(200, "OK", userServices.getCurrentUserProfile());
    }

    @GetMapping("/my-tasks")
    public ApiResponse getUserTasks() {
        return ApiResponse.success(200, "OK", userServices.getUserTasks());
    }

    @PostMapping("/add-comment")
    public ApiResponse addCommentToTask(@RequestBody TaskCommentRequestDTO commentRequestDTO) {
        TaskCommentResponseDTO commentBody = commentServices.addCommentToTask(commentRequestDTO);
        return ApiResponse.success(200, "OK", commentBody);
    }

    @GetMapping("/task-comments/{taskId}")
    public ApiResponse getCommentsByTaskId(@PathVariable Long taskId) {
        return ApiResponse.success(200, "OK", commentServices.getCommentsByTaskId(taskId));
    }

}
