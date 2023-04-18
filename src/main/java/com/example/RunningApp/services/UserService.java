package com.example.RunningApp.services;

import java.util.List;

import com.example.RunningApp.models.User;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    User getUserByEmail(String email);
	List<User> getAllUsers();
	User saveUser(User user);
	void deleteUser(Long id);
	User updateUser(Long id, User updatedUser);
}

