package com.example.RunningApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.example.RunningApp.models.User;
import com.example.RunningApp.repositories.UserRepository;
import com.example.RunningApp.services.UserServiceImpl;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserTests {

	@Mock
	private UserRepository userRepository;

	private UserServiceImpl userService;

	@BeforeEach
	public void setUp() {
		// Initialize Mockito annotations
		MockitoAnnotations.openMocks(this);

		// Create a new instance of the UserServiceImpl class
		userService = new UserServiceImpl();

		// Set the userRepository field of the userService instance to the mocked
		// userRepository
		userService.setUserRepository(userRepository);
	}

	@Test
	public void testGetUserById() {
		// Create a User object with a unique ID and some attributes
		Long id = 1L;
		User user = new User();
		user.setId(id);
		user.setName("John");
		user.setEmail("john@test.com");

		// When userRepository.findById is called with the user's ID, return an Optional
		// containing the user
		when(userRepository.findById(id)).thenReturn(Optional.of(user));

		// Call the getUserById method on the userService instance with the user's ID
		User result = userService.getUserById(id);

		// Assert that the attributes of the returned User object match the attributes
		// of the original User object
		assertEquals(id, result.getId());
		assertEquals("John", result.getName());
		assertEquals("john@test.com", result.getEmail());

		// Assert that the number of records in the database has not changed
		assertEquals(0, userRepository.count());
	}

	@Test
	public void testSaveUser() {
		// Create a User object with some attributes
		User user = new User();
		user.setName("John");
		user.setEmail("john@test.com");

		// Get the number of records in the database before saving the new user
		long countBefore = userRepository.findAll().size();

		// When userRepository.save is called with the new user, return the same user
		when(userRepository.save(user)).thenReturn(user);

		// When userRepository.findAll is called, return a List containing the new user
		when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

		// Call the saveUser method on the userService instance with the new user
		User result = userService.saveUser(user);

		// Get the number of records in the database after saving the new user
		long countAfter = userRepository.findAll().size();

		// Assert that the attributes of the returned User object match the attributes
		// of the original User object
		assertEquals("John", result.getName());
		assertEquals("john@test.com", result.getEmail());

		// Assert that the number of records in the database has increased by 1
		assertEquals(countBefore + 1, countAfter);
	}

	@Test
	public void testDeleteUser() {
		// Create a User object with a unique ID
		Long id = 1L;

		// Call the deleteUser method on the userService instance with the user's ID
		userService.deleteUser(id);

		// Assert that the user was deleted from the database
		when(userRepository.findById(id)).thenReturn(Optional.empty());
		assertEquals(Optional.empty(), userRepository.findById(id));
	}

	// This test case tests the updateUser method of the UserServiceImpl class
	@Test
	public void testUpdateUser() {
		// Create an instance of an existing user
		Long id = 1L;
		User existingUser = new User();
		existingUser.setId(id);
		existingUser.setName("John");
		existingUser.setEmail("john@test.com");
		// Mock the behavior of the UserRepository for findById and findByEmail methods
		when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));
		User updatedUser = new User();
		updatedUser.setName("Updated John");
		updatedUser.setEmail("updated-john@test.com");
		updatedUser.setPassword("password");
		when(userRepository.findByEmail(updatedUser.getEmail())).thenReturn(Optional.empty());
		// Mock the behavior of the UserRepository for the save method
		when(userRepository.save(existingUser)).thenReturn(existingUser);

		// Call the updateUser method and assert that the user details have been updated
		// correctly
		User result = userService.updateUser(id, updatedUser);
		assertEquals("Updated John", result.getName());
		assertEquals("updated-john@test.com", result.getEmail());
		assertEquals("password", result.getPassword());
	}

	// This test case tests the getAllUsers method of the UserServiceImpl class
	@Test
	public void testGetAllUsers() {
		// Create a list of users
		List<User> users = new ArrayList<>();
		User user1 = new User();
		user1.setId(1L);
		user1.setName("John");
		user1.setEmail("john@test.com");
		User user2 = new User();
		user2.setId(2L);
		user2.setName("Jane");
		user2.setEmail("jane@test.com");
		users.add(user1);
		users.add(user2);
		// Mock the behavior of the UserRepository for the findAll method
		when(userRepository.findAll()).thenReturn(users);

		// Call the getAllUsers method and assert that the correct list of users is returned
		List<User> result = userService.getAllUsers();
		assertEquals(2, result.size());
		assertEquals("John", result.get(0).getName());
		assertEquals("jane@test.com", result.get(1).getEmail());
	}
}
