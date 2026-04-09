package com.example.chat_application.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private long id;
	private String email;
	private String name;
	private String password;
	private String image;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	
	private String provider;
}
