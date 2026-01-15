package com.varosha.springboot.taskmanagement.Controller.EmployeeController;

import com.varosha.springboot.taskmanagement.DTO.standUp.StandUpRequestDTO;
import com.varosha.springboot.taskmanagement.DTO.standUp.StandUpResponseDTO;
import com.varosha.springboot.taskmanagement.DTO.task.TaskCommentRequestDTO;
import com.varosha.springboot.taskmanagement.DTO.task.TaskCommentResponseDTO;
import com.varosha.springboot.taskmanagement.DTO.task.TaskResponseDTO;
import com.varosha.springboot.taskmanagement.DTO.user.UserResponseDTO;
import com.varosha.springboot.taskmanagement.Services.StandUpServices;
import com.varosha.springboot.taskmanagement.Services.TaskCommentServices;
import com.varosha.springboot.taskmanagement.Services.UserServices;
import com.varosha.springboot.taskmanagement.taskCommon.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class EmployeeController {

    private final UserServices userServices;
    private final TaskCommentServices commentServices;
    private final StandUpServices standUpServices;
    public EmployeeController(UserServices userServices, StandUpServices standUpServices,
                              TaskCommentServices commentServices) {
        this.userServices = userServices;
        this.commentServices = commentServices;
        this.standUpServices = standUpServices;
    }

    @GetMapping("/my-profile")
    public ApiResponse<UserResponseDTO> getUserProfile() {
        return ApiResponse.success(200, "OK", userServices.getCurrentUserProfile());
    }

    @GetMapping("/my-tasks")
    public ApiResponse<List<TaskResponseDTO>> getUserTasks() {
        return ApiResponse.success(200, "OK", userServices.getUserTasks());
    }

    @PostMapping("/add-comment")
    public ApiResponse<TaskCommentResponseDTO> addCommentToTask(@RequestBody TaskCommentRequestDTO commentRequestDTO) {
        TaskCommentResponseDTO commentBody = commentServices.addCommentToTask(commentRequestDTO);
        return ApiResponse.success(200, "OK", commentBody);
    }

    @GetMapping("/task-comments/{taskId}")
    public ApiResponse<List<TaskCommentResponseDTO>> getCommentsByTaskId(@PathVariable Long taskId) {
        return ApiResponse.success(200, "OK", commentServices.getCommentsByTaskId(taskId));
    }

    @PostMapping("/submit-standup")
    public ApiResponse<StandUpResponseDTO> submit(@RequestBody StandUpRequestDTO dto) {
        return ApiResponse.success(200, "StandUp submitted Successfully!!!", standUpServices.submitStandUp(dto));
    }

}
