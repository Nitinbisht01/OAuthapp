package com.example.chat_application.service.impl;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.example.chat_application.model.User;
import com.example.chat_application.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword() == null ? "" : user.getPassword())
                .authorities("USER") // dummy authority (required by Spring)
                .build();
    }
}