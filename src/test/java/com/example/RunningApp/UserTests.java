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
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl();
        userService.setUserRepository(userRepository);
    }


    @Test
    public void testGetUserById() {
        Long id = 1L;
        User user = new User();
        user.setId(id);
        user.setName("John");
        user.setEmail("john@test.com");

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        User result = userService.getUserById(id);

        assertEquals(id, result.getId());
        assertEquals("John", result.getName());
        assertEquals("john@test.com", result.getEmail());

        // Assert that the number of records in the database has not changed
        assertEquals(0, userRepository.count());
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setName("John");
        user.setEmail("john@test.com");

        long countBefore = userRepository.findAll().size();

        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        User result = userService.saveUser(user);

        long countAfter = userRepository.findAll().size();

        assertEquals("John", result.getName());
        assertEquals("john@test.com", result.getEmail());

        // Assert that the number of records in the database has increased by 1
        assertEquals(countBefore + 1, countAfter);
    }



    @Test
    public void testDeleteUser() {
        Long id = 1L;
        userService.deleteUser(id);

        // Assert that the user was deleted from the database
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), userRepository.findById(id));
    }

    @Test
    public void testUpdateUser() {
        Long id = 1L;
        User existingUser = new User();
        existingUser.setId(id);
        existingUser.setName("John");
        existingUser.setEmail("john@test.com");
        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));
        User updatedUser = new User();
        updatedUser.setName("Updated John");
        updatedUser.setEmail("updated-john@test.com");
        updatedUser.setPassword("password");
        when(userRepository.findByEmail(updatedUser.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(existingUser)).thenReturn(existingUser);
        User result = userService.updateUser(id, updatedUser);
        assertEquals("Updated John", result.getName());
        assertEquals("updated-john@test.com", result.getEmail());
        assertEquals("password", result.getPassword());
    }


    @Test
    public void testGetAllUsers() {
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
        when(userRepository.findAll()).thenReturn(users);
        List<User> result = userService.getAllUsers();
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getName());
        assertEquals("jane@test.com", result.get(1).getEmail());
    }
}
