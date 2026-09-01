package com.petcare.user_service.controller;

import com.petcare.user_service.dto.LoginRequest;
import com.petcare.user_service.dto.LoginResponse;
import com.petcare.user_service.entity.User;
import com.petcare.user_service.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Register
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {

        User registeredUser = userService.registerUser(user);

        return new ResponseEntity<>(
                registeredUser,
                HttpStatus.CREATED
        );
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(
            @RequestBody LoginRequest loginRequest) {

        LoginResponse response =
                userService.loginUser(loginRequest);

        return ResponseEntity.ok(response);
    }

    // Get User Profile
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                userService.getUserById(id)
        );
    }
}