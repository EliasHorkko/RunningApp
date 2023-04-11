package com.example.RunningApp.services;

import com.example.RunningApp.models.User;

public interface UserService {
    User saveUser(User user);
    User getUserByUsername(String username);
    User createUser(User user);
}
