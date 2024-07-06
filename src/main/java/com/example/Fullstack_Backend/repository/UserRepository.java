package com.example.Fullstack_Backend.repository;

import com.example.Fullstack_Backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFullNameContainingIgnoreCase(String fullName);
    User findByEmail(String email);
}
