package com.example.demo.Service.Users;

import com.example.demo.entity.User;

public interface UserService {
    User create(User user);
    User getByUsername(String username);

    User login(String username, String password);
    User resetPassword(String username, String email);
    User changePassword(String username, String oldPassword, String newPassword);
}
