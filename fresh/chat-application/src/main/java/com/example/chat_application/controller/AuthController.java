package com.example.chat_application.controller;

import java.net.ResponseCache;
import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chat_application.dto.LoginRequest;
import com.example.chat_application.dto.RegisterRequest;
import com.example.chat_application.dto.UserDto;
import com.example.chat_application.dto.UserResponseDto;
import com.example.chat_application.service.AuthService;


import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")

@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        String token = authService.login(request);

        return ResponseEntity.ok(
                Collections.singletonMap("token", token)
        );
    }
}
