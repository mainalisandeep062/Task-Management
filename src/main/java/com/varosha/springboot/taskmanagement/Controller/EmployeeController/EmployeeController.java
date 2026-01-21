package com.varosha.springboot.taskmanagement.Controller.EmployeeController;

import com.varosha.springboot.taskmanagement.DTO.standUp.StandUpRequestDTO;
import com.varosha.springboot.taskmanagement.DTO.standUp.StandUpResponseDTO;
import com.varosha.springboot.taskmanagement.DTO.taskComment.TaskCommentRequestDTO;
import com.varosha.springboot.taskmanagement.DTO.taskComment.TaskCommentResponseDTO;
import com.varosha.springboot.taskmanagement.DTO.task.TaskResponseDTO;
import com.varosha.springboot.taskmanagement.DTO.user.UserResponseDTO;
import com.varosha.springboot.taskmanagement.Enums.TaskStatus;
import com.varosha.springboot.taskmanagement.Services.StandUpServices;
import com.varosha.springboot.taskmanagement.Services.TaskCommentServices;
import com.varosha.springboot.taskmanagement.Services.UserServices;
import com.varosha.springboot.taskmanagement.taskCommon.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "General Employee Panel")
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

    @Operation(
            summary = "View Own profile",
            description = "Fetch the profile of currently logged in User")
    @GetMapping("/my-profile")
    public ApiResponse<UserResponseDTO> getUserProfile() {
        return ApiResponse.success(200, "OK", userServices.getCurrentUserProfile());
    }

    @Operation(
            summary = "View task assigned to current user",
            description = "Fetch all the tasks assigned to the current user.")
    @GetMapping("/my-tasks")
    public ApiResponse<List<TaskResponseDTO>> getUserTasks() {
        return ApiResponse.success(200, "OK", userServices.getUserTasks());
    }

    @Operation(
            summary = "Add comment to a certain task",
            description = "Admins can add comment to any task. " +
                    "But Employee can only add comment to those task which are assigned to them.")
    @PostMapping("/add-comment")
    public ApiResponse<TaskCommentResponseDTO> addCommentToTask(@RequestBody TaskCommentRequestDTO commentRequestDTO) {
        TaskCommentResponseDTO commentBody = commentServices.addCommentToTask(commentRequestDTO);
        return ApiResponse.success(200, "Comment has been added to the task successfully!!", commentBody);
    }

    @Operation(
            summary = "Get comments of certain task",
            description = "User may view comment on a certain task only if it was assigned to them")
    @GetMapping("/task-comments/{taskId}")
    public ApiResponse<List<TaskCommentResponseDTO>> getCommentsByTaskId(@PathVariable Long taskId) {
        return ApiResponse.success(200, "OK", commentServices.getCommentsByTaskId(taskId));
    }

    @Operation(
            summary = "Submit StandUp",
            description = "Every User may Submit a Standup only once a day")
    @PostMapping("/submit-standup")
    public ApiResponse<StandUpResponseDTO> submit(@RequestBody StandUpRequestDTO dto) {
        return ApiResponse.success(200, "StandUp submitted Successfully!!!", standUpServices.submitStandUp(dto));
    }

    @Operation(
            summary = "Update assigned Task's status",
            description = " Users may update status of their assigned task (IN_PROGRESS, DONE)")
    @PatchMapping("/update_task")
    public ApiResponse<TaskResponseDTO> updateTaskStatusById(@RequestParam Long taskId,
                                                             @RequestParam TaskStatus status){
        return ApiResponse.success(200, "Task Status updated Successfully", userServices.updateTaskStatusById(taskId, status));
    }

}
