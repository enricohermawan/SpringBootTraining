package com.example.demo.Service.Users;

import com.example.demo.entity.User;
import com.example.demo.repository.User.Repository;
import com.example.demo.utils.MD5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserImpl implements UserService{
    @Autowired
    private final Repository userRepository;

    public UserImpl(Repository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public User login(String username, String password) {

        String hashedPassword = MD5Hash.md5(password);

        User user = getByUsername(username);
        if(hashedPassword.equalsIgnoreCase(user.getPasswordHash())){
            return user;
        }

        return null;
    }

    @Override
    public User resetPassword(String username, String email) {
        User user = getByUsername(username);
        if(!user.getEmail().equals(email)){
            return null;
        }

        int tempPassword = 10000000 + new Random().nextInt(90000000);
        user.setPasswordHash(MD5Hash.md5(String.valueOf(tempPassword)));
        userRepository.save(user);

        user.setPasswordHash(String.valueOf(tempPassword));

        return user;
    }
}

