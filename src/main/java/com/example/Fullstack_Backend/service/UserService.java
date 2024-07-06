package com.example.Fullstack_Backend.service;

import com.example.Fullstack_Backend.model.User;
import com.example.Fullstack_Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public User createUser(User user) {

        User googleUser=userRepository.findByEmail(user.getEmail());

        if(googleUser!=null) {
            System.out.println("User already exists");
            return googleUser;
        }
        return userRepository.save(user);
    }

    public Optional<User> updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setGoogleId(userDetails.getGoogleId());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setFullName(userDetails.getFullName());
            existingUser.setProfilePictureUrl(userDetails.getProfilePictureUrl());
            existingUser.setCreatedAt(userDetails.getCreatedAt());
            return userRepository.save(existingUser);
        });
    }

    public Optional<User> patchUser(Long id, User userDetails) {
        return userRepository.findById(id).map(existingUser -> {
            if (userDetails.getGoogleId() != null) {
                existingUser.setGoogleId(userDetails.getGoogleId());
            }
            if (userDetails.getEmail() != null) {
                existingUser.setEmail(userDetails.getEmail());
            }
            if (userDetails.getFullName() != null) {
                existingUser.setFullName(userDetails.getFullName());
            }
            if (userDetails.getProfilePictureUrl() != null) {
                existingUser.setProfilePictureUrl(userDetails.getProfilePictureUrl());
            }
            return userRepository.save(existingUser);
        });
    }

    // Search users by full name
    public List<User> searchUsersByName(String fullName) {
        return userRepository.findByFullNameContainingIgnoreCase(fullName);
    }
    // Delete a user
    public Optional<Void> deleteUser(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return null;
        });
    }
}
