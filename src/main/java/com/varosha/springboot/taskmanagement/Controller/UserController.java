package com.varosha.springboot.taskmanagement.Controller;

import com.varosha.springboot.taskmanagement.DTO.user.CreateUserDTO;
import com.varosha.springboot.taskmanagement.DTO.user.UserResponseDTO;
import com.varosha.springboot.taskmanagement.Services.UserServices;
import com.varosha.springboot.taskmanagement.taskCommon.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/admin/create-user")
    public ApiResponse createUser(@RequestBody CreateUserDTO createUserDTO) {
        UserResponseDTO createdUser = userServices.createUser(createUserDTO);
        return ApiResponse.success(201, "Created", createdUser);
    }

    @GetMapping("/all-user")
    public ApiResponse getAllUsers() {
        return ApiResponse.success(200, "OK", userServices.getAllUsers());
    }

    @GetMapping("/name")
    public ApiResponse getUserByFullName(@RequestParam String fullName) {
        return ApiResponse.success(200, "OK", userServices.getUserByFullName(fullName));
    }

    @GetMapping("/email/{email}")
    public ApiResponse getUserByEmail(@PathVariable String email) {
        return ApiResponse.success(200, "OK", userServices.getUserByEmail(email));
    }

    @PatchMapping("/{userId}/deactivate")
    public ApiResponse deactivateUserById(@PathVariable Long userId) {
        return ApiResponse.success(200, "OK", userServices.deactivateUsrById(userId));
    }
}