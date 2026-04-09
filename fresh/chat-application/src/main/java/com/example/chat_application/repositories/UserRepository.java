package com.example.chat_application.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chat_application.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User>findByEmail(String email);
	boolean existsByEmail(String email);

}
