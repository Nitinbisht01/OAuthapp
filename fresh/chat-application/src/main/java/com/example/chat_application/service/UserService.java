package com.example.chat_application.service;

import java.util.List;

import com.example.chat_application.dto.UserDto;
import com.example.chat_application.model.User;

public interface UserService {

	public UserDto newUser(UserDto user);
	
	public UserDto getUserByEmail(String email);
	
	public UserDto updateUser (UserDto user,String UserId);
	
	public void userDelete(String userId);
	
	public UserDto getUserById(String id);
	
	public List<User> allUser();
}
