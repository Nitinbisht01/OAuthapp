package com.example.chat_application.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.chat_application.dto.UserDto;
import com.example.chat_application.model.User;
import com.example.chat_application.repositories.UserRepository;
import com.example.chat_application.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // ✅ Injected

    // ✅ CREATE USER
    @Override
    public UserDto newUser(UserDto userDto) {

        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = modelMapper.map(userDto, User.class);

        // 🔐 Encode password before saving
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDto.class);
    }

    // ✅ GET USER BY EMAIL
    @Override
    public UserDto getUserByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        return modelMapper.map(user, UserDto.class);
    }

    // ✅ UPDATE USER
    @Override
    public UserDto updateUser(UserDto userDto, String userId) {

        Long id = Long.parseLong(userId);

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        existingUser.setName(userDto.getName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setProvider(userDto.getProvider());

        // 🔐 Encode password ONLY if updated
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        User updatedUser = userRepository.save(existingUser);

        return modelMapper.map(updatedUser, UserDto.class);
    }

    // ✅ DELETE USER
    @Override
    public void userDelete(String userId) {

        Long id = Long.parseLong(userId);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        userRepository.delete(user);
    }

    // ✅ GET USER BY ID
    @Override
    public UserDto getUserById(String id) {

        Long userId = Long.parseLong(id);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        return modelMapper.map(user, UserDto.class);
    }

    // ✅ GET ALL USERS
    @Override
    public List<User> allUser() {
        return userRepository.findAll();
    }
}