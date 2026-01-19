package com.varosha.springboot.taskmanagement.ServicesImpl;

import com.varosha.springboot.taskmanagement.DTO.notification.NotificationRequestDTO;
import com.varosha.springboot.taskmanagement.DTO.task.CreateTaskDTO;
import com.varosha.springboot.taskmanagement.DTO.task.TaskResponseDTO;
import com.varosha.springboot.taskmanagement.Enums.NotificationType;
import com.varosha.springboot.taskmanagement.Enums.TaskStatus;
import com.varosha.springboot.taskmanagement.Models.Task;
import com.varosha.springboot.taskmanagement.Repository.TaskRepo;
import com.varosha.springboot.taskmanagement.Repository.UserRepo;
import com.varosha.springboot.taskmanagement.Configuration.ExtractEmail;
import com.varosha.springboot.taskmanagement.Services.TaskServices;
import com.varosha.springboot.taskmanagement.converter.TaskConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServicesImpl implements TaskServices {
    private final TaskRepo taskRepo;
    private final TaskConverter taskConverter;
    private final UserRepo userRepo;
    private final NotificationServicesImpl notificationService;

    @Override
    public TaskResponseDTO createTask(CreateTaskDTO createTaskDTO) {
        Task task = taskConverter.toEntity(createTaskDTO);
        task.setStatus(TaskStatus.TODO);
        task.setCreatedBy(userRepo.findByEmail(new ExtractEmail().getEmail()).
                orElseThrow(() -> new RuntimeException("User not found with email: " + new ExtractEmail().getEmail())));
        task.setCreatedAt(LocalDateTime.now());
        Task createdTask = taskRepo.save(task);

        notificationService.send(NotificationRequestDTO.builder()
                    .recipientId(task.getAssignee().getId())
                    .title("You were assigned a new task! \r\n"
                            + task.getTitle() + "Due :" + task.getDueDate())
                    .message(task.getDescription())
                    .type(NotificationType.TASK_ASSIGNED)
                    .build());

        return taskConverter.toTaskResponseDTO(createdTask);
    }

    @Override
    public List<TaskResponseDTO> getAllTask() {
        return taskRepo.findAll()
                .stream()
                .map(taskConverter::toTaskResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDTO findByName(String title) {
        return taskRepo.findByTitle(title)
                .map(taskConverter::toTaskResponseDTO)
                .orElseThrow(() -> new RuntimeException("Task not found with Title : " + title));
    }

    @Override
    public List<TaskResponseDTO> findByStatus(TaskStatus status) {
        return taskRepo.findByStatus(status)
                .stream()
                .map(taskConverter::toTaskResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponseDTO> getTaskByAssigneeEmail(String assigneeEmail) {
        Long id = userRepo.findByEmail(assigneeEmail).orElseThrow(() -> new RuntimeException("User not found!!!")).getId();
        return taskRepo.findByAssigneeId(id)
                .stream()
                .map(taskConverter::toTaskResponseDTO)
                .collect(Collectors.toList());
    }


}
