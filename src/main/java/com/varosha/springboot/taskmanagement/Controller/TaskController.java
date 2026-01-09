package com.varosha.springboot.taskmanagement.Controller;

import com.varosha.springboot.taskmanagement.DTO.task.CreateTaskDTO;
import com.varosha.springboot.taskmanagement.DTO.task.TaskResponseDTO;
import com.varosha.springboot.taskmanagement.Enums.TaskStatus;
import com.varosha.springboot.taskmanagement.Services.TaskServices;
import com.varosha.springboot.taskmanagement.taskCommon.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskServices taskServices;
    public TaskController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    @PostMapping
    public ApiResponse createTask(@RequestBody CreateTaskDTO createTaskDTO){
        TaskResponseDTO createdTask = taskServices.createTask(createTaskDTO);
        return ApiResponse.success(200, "OK", createdTask);
    }

    @GetMapping
    public ApiResponse GetAllTasks(){
        return ApiResponse.success(200, "OK", taskServices.getAllTask());
    }

    @GetMapping("/title")
    public ApiResponse getTaskByTitle(@RequestParam String title){
        return ApiResponse.success(200, "OK", taskServices.findByName(title));
    }

    @GetMapping("/status")
    public ApiResponse getTaskByStatus(@RequestParam String status){
        return ApiResponse.success(200, "OK", taskServices.findByStatus(TaskStatus.valueOf(status)));
    }
}
