package com.varosha.springboot.taskmanagement.Controller.AdminController;

import com.varosha.springboot.taskmanagement.DTO.user.CreateUserDTO;
import com.varosha.springboot.taskmanagement.DTO.user.UserResponseDTO;
import com.varosha.springboot.taskmanagement.Services.AdminServices;
import com.varosha.springboot.taskmanagement.taskCommon.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminServices adminServices;

    public AdminController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }

    @PostMapping("/create-user")
    public ApiResponse createUser(@RequestBody CreateUserDTO createUserDTO) {
        UserResponseDTO createdUser = adminServices.createUser(createUserDTO, null);
        return ApiResponse.success(201, "Created", createdUser);
    }

    @GetMapping("/all-user")
    public ApiResponse getAllUsers() {
        return ApiResponse.success(200, "OK", adminServices.getAllUsers());
    }

    @GetMapping("/name")
    public ApiResponse getUserByFullName(@RequestParam String fullName) {
        return ApiResponse.success(200, "OK", adminServices.getUserByFullName(fullName));
    }

    @GetMapping("/email")
    public ApiResponse getUserByEmail(@RequestParam String email) {
        return ApiResponse.success(200, "OK", adminServices.getUserByEmail(email));
    }

    @PatchMapping("/{userId}/deactivate")
    public ApiResponse deactivateUserById(@PathVariable Long userId) {
        return ApiResponse.success(200,
                "User Deactivated Successfully!!",
                adminServices.deactivateUsrById(userId, null));
    }
}