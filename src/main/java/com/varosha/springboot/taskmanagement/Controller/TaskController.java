package com.varosha.springboot.taskmanagement.Controller;

import com.varosha.springboot.taskmanagement.DTO.task.CreateTaskDTO;
import com.varosha.springboot.taskmanagement.DTO.task.TaskResponseDTO;
import com.varosha.springboot.taskmanagement.Enums.TaskStatus;
import com.varosha.springboot.taskmanagement.Services.TaskServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskServices taskServices;
    public TaskController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody CreateTaskDTO createTaskDTO){
        TaskResponseDTO createdTask = taskServices.createTask(createTaskDTO);
        return new ResponseEntity<>(createdTask, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> GetAllTasks(){
        return ResponseEntity.ok(taskServices.getAllTask());
    }

    @GetMapping("/title")
    public ResponseEntity<TaskResponseDTO> getTaskByTitle(@RequestParam String title){
        return ResponseEntity.ok(taskServices.findByName(title));
    }

    @GetMapping("/status")
    public ResponseEntity<TaskResponseDTO> getTaskByStatus(@RequestParam String status){
        return ResponseEntity.ok(taskServices.findByStatus(TaskStatus.valueOf(status)));
    }
}
