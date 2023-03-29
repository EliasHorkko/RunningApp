package com.example.RunningApp.services;

import com.example.RunningApp.UserService;
import com.example.RunningApp.models.User;
import com.example.RunningApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {
	    return userRepository.save(user);
	}

}