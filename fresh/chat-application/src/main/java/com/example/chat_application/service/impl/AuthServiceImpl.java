package com.example.chat_application.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.chat_application.dto.LoginRequest;
import com.example.chat_application.dto.RegisterRequest;
import com.example.chat_application.dto.UserDto;
import com.example.chat_application.model.User;
import com.example.chat_application.repositories.UserRepository;
import com.example.chat_application.service.AuthService;
import com.example.chat_application.service.impl.JwtService;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    // ✅ REGISTER
    @Override
    public UserDto registerUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setProvider("LOCAL");

        // 🔐 encode password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);

        savedUser.setPassword(null); // ❗ prevent leaking

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔥 Provider check (important for OAuth future)
        if (!"LOCAL".equals(user.getProvider())) {
            throw new RuntimeException("Use " + user.getProvider() + " login");
        }

        // 🔐 Password check
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtService.generateToken(user.getEmail());
    }

	
}