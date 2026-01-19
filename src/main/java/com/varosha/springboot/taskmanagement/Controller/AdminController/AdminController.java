package com.varosha.springboot.taskmanagement.Controller.AdminController;

import com.varosha.springboot.taskmanagement.DTO.user.CreateUserDTO;
import com.varosha.springboot.taskmanagement.DTO.user.UserResponseDTO;
import com.varosha.springboot.taskmanagement.Services.AdminServices;
import com.varosha.springboot.taskmanagement.taskCommon.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin Employee panel")
public class AdminController {

    private final AdminServices adminServices;

    public AdminController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }

    @Operation(
            summary = "Admins create new user (Admin or Employee)",
            description = "Only Admins are allowed to create a new user.")
    @PostMapping("/create-user")
    public ApiResponse<UserResponseDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {
        UserResponseDTO createdUser = adminServices.createUser(createUserDTO, null);
        return ApiResponse.success(201, "Created", createdUser);
    }

    @Operation(
            summary = "Admins may view and read profiles of every Users",
            description = "Only Admins are allowed to read and view profiles of other users.")
    @GetMapping("/all-user")
    public ApiResponse<List<UserResponseDTO>> getAllUsers() {
        return ApiResponse.success(200, "OK", adminServices.getAllUsers());
    }

    @Operation(summary = "Admins fetch profile of certain user by their Full name.")
    @GetMapping("/name")
    public ApiResponse<UserResponseDTO> getUserByFullName(@RequestParam String fullName) {
        return ApiResponse.success(200, "OK", adminServices.getUserByFullName(fullName));
    }

    @Operation(summary = "Admins can Fetch profile of certain user by their email.")
    @GetMapping("/email")
    public ApiResponse<UserResponseDTO> getUserByEmail(@RequestParam String email) {
        return ApiResponse.success(200, "OK", adminServices.getUserByEmail(email));
    }

    @Operation(summary = "Deactivate users By Id",
               description = "Admins may perform soft delete(deactivate) on a user by ID" )
    @PatchMapping("/{userId}/deactivate")
    public ApiResponse<UserResponseDTO> deactivateUserById(@PathVariable Long userId) {
        return ApiResponse.success(200,
                "User Deactivated Successfully!!",
                adminServices.deactivateUsrById(userId, null));
    }
}