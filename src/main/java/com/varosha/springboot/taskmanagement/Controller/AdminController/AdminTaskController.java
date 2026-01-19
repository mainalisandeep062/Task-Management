package com.varosha.springboot.taskmanagement.Controller.AdminController;

import com.varosha.springboot.taskmanagement.DTO.task.CreateTaskDTO;
import com.varosha.springboot.taskmanagement.DTO.task.TaskResponseDTO;
import com.varosha.springboot.taskmanagement.Enums.TaskStatus;
import com.varosha.springboot.taskmanagement.Services.TaskServices;
import com.varosha.springboot.taskmanagement.taskCommon.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@Tag(name = "Admin Task panel")
public class AdminTaskController {

    private final TaskServices taskServices;
    public AdminTaskController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    @Operation(
            summary = "Create a new Task ",
            description = "Admins may create a new task and assign to existing user.")
    @PostMapping
    public ApiResponse<TaskResponseDTO> createTask(@RequestBody CreateTaskDTO createTaskDTO){
        TaskResponseDTO createdTask = taskServices.createTask(createTaskDTO);
        return ApiResponse.success(200, "OK", createdTask);
    }

    @Operation(
            summary = "Fetch list of all task",
            description = "Admins can view all the tasks existing in database.")
    @GetMapping
    public ApiResponse<List<TaskResponseDTO>> GetAllTasks(){
        return ApiResponse.success(200, "OK", taskServices.getAllTask());
    }

    @Operation(
            summary = "Fetch task by status",
            description = "Admins may fetch task by status (DONE, IN_PROGRESS, TODO")
    @GetMapping("/status")
    public ApiResponse<List<TaskResponseDTO>> getTaskByStatus(@RequestParam TaskStatus status){
        return ApiResponse.success(200, "OK", taskServices.findByStatus(status));
    }

    @Operation(
            summary = "Fetch Tasks by Assignee email",
            description = "Fetch list of task assigned to a certain assignee.")
    @GetMapping("/assignee")
    public ApiResponse<List<TaskResponseDTO>> getTaskByAssigneeEmail(@RequestParam String email){
        return ApiResponse.success(200, "OK", taskServices.getTaskByAssigneeEmail(email));
    }
}
