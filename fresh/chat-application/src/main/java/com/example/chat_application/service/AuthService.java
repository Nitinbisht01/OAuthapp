package com.example.chat_application.service;

import com.example.chat_application.dto.LoginRequest;
import com.example.chat_application.dto.RegisterRequest;
import com.example.chat_application.dto.UserDto;


public interface AuthService {

    UserDto registerUser(RegisterRequest request);

    String login(LoginRequest request);
}