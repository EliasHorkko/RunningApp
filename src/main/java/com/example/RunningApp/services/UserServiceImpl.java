package com.example.RunningApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.RunningApp.models.User;
import com.example.RunningApp.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository; // An instance of UserRepository is used for data access.

	@Override
	public User getUserById(Long id) {
	    // Retrieve user data by id.
	    Optional<User> optionalUser = userRepository.findById(id);
	    return optionalUser.orElse(null); // Return the user object, or null if not found.
	}

	@Override
	public User saveUser(User user) {
	    // Save a new user or update an existing one.
	    return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
	    // Delete user data by id.
	    userRepository.deleteById(id);
	}

	@Override
	public User updateUser(Long id, User updatedUser) {
	    // Retrieve user data by id.
	    Optional<User> optionalUser = userRepository.findById(id);
	    if (optionalUser.isPresent()) { // Check if the user exists.
	        User user = optionalUser.get();
	        if (!user.getEmail().equals(updatedUser.getEmail()) && userRepository.findByEmail(updatedUser.getEmail()).isPresent()) {
	            // Check if the updated email address already exists in the database.
	            throw new IllegalArgumentException("Email already in use");
	        }
	        user.setName(updatedUser.getName());          
	        user.setEmail(updatedUser.getEmail());
	        user.setPassword(updatedUser.getPassword());
	        return userRepository.save(user); // Save the updated user data and return the updated user object.
	    } else {
	        throw new IllegalArgumentException("User not found");
	    }
	}

	@Override
	public List<User> getAllUsers() {
	    // Retrieve a list of all users.
	    return userRepository.findAll();
	}

	@Override
	public User createUser(User user) {
	    if (userRepository.findByEmail(user.getEmail()).isPresent()) {
	        // Check if the email address already exists in the database.
	        throw new IllegalArgumentException("Email already in use");
	    }
	    return userRepository.save(user); // Save the new user data and return the new user object.
	}

	@Override
	public User getUserByEmail(String email) {
	    // Retrieve user data by email address.
	    Optional<User> optionalUser = userRepository.findByEmail(email);
	    return optionalUser.orElse(null); // Return the user object, or null if not found.
	}

	public void setUserRepository(UserRepository userRepository) {
	    // Setter method for UserRepository instance.
	    this.userRepository = userRepository;
	}

	@Override
	public User loginUser(String username, String password) {
	    // Retrieve user data by username and password.
	    Optional<User> optionalUser = userRepository.findByUsernameAndPassword(username, password);
	    return optionalUser.orElse(null); // Return the user object, or null if not found.
	}
}
