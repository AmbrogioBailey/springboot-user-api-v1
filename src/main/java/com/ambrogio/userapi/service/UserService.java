package com.ambrogio.userapi.service;

import com.ambrogio.userapi.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    private int nextId = 1;

    public UserService() {
                addUser(new User(null, "Bert", "bert@example.com"));
                addUser(new User(null, "Tim", "tim@example.com"));
    }
    public List<User> getAllUsers() {
        return users;
    }
    public User addUser(User user) {
        user.setId(nextId++);
        users.add(user);
        return user;
    }
    public Optional<User> getUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }
    public Optional<User> updateUser(int id, User updatedUser) {
        for (User user : users) {
            if (user.getId() == id) {
                user.setName(updatedUser.getName());
                user.setEmail(updatedUser.getEmail());
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
    public boolean deleteUser(int id) {
        return users.removeIf(user -> user.getId() == id);
    }
}
