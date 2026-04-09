package com.example.chat_application.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.chat_application.dto.UserDto;
import com.example.chat_application.model.User;
import com.example.chat_application.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ✅ Get logged-in user (MOST IMPORTANT 🔥)
    @GetMapping("/me")
    public ResponseEntity<?> getMe(Authentication authentication) {

        String email = authentication.getName();

        // 🔥 fetch user from DB
        UserDto user = userService.getUserByEmail(email);

        return ResponseEntity.ok(user);
    }
    // ✅ Get all users (for testing)
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.allUser());
    }
}