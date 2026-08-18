package com.petadoption.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petadoption.user.entity.User;
import com.petadoption.user.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    // CREATE
    public User saveUser(User user) {
        return repository.save(user);
    }

    // READ ALL
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    // READ BY ID
    public Optional<User> getUserById(Long id) {
        return repository.findById(id);
    }

    // UPDATE
    public User updateUser(Long id, User user) {

        User existingUser = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setAddress(user.getAddress());
        existingUser.setPassword(user.getPassword());

        return repository.save(existingUser);
    }

    // DELETE
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}