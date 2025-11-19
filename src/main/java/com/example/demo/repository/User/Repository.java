package com.example.demo.repository.User;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<User, String> {
    User getByUsername(String username);
}
