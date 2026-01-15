package com.varosha.springboot.taskmanagement.Controller.AdminController;

import com.varosha.springboot.taskmanagement.DTO.task.CreateTaskDTO;
import com.varosha.springboot.taskmanagement.DTO.task.TaskResponseDTO;
import com.varosha.springboot.taskmanagement.Enums.TaskStatus;
import com.varosha.springboot.taskmanagement.Services.TaskServices;
import com.varosha.springboot.taskmanagement.taskCommon.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class AdminTaskController {

    private final TaskServices taskServices;
    public AdminTaskController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    @PostMapping
    public ApiResponse<TaskResponseDTO> createTask(@RequestBody CreateTaskDTO createTaskDTO){
        TaskResponseDTO createdTask = taskServices.createTask(createTaskDTO);
        return ApiResponse.success(200, "OK", createdTask);
    }

    @GetMapping
    public ApiResponse<List<TaskResponseDTO>> GetAllTasks(){
        return ApiResponse.success(200, "OK", taskServices.getAllTask());
    }

    @GetMapping("/status")
    public ApiResponse<List<TaskResponseDTO>> getTaskByStatus(@RequestParam String status){
        return ApiResponse.success(200, "OK", taskServices.findByStatus(TaskStatus.valueOf(status)));
    }

    @GetMapping("/assignee")
    public ApiResponse<List<TaskResponseDTO>> getTaskByAssigneeEmail(@RequestParam String email){
        return ApiResponse.success(200, "OK", taskServices.getTaskByAssigneeEmail(email));
    }
}
