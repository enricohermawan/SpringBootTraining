package com.example.demo.controller.User;

import com.example.demo.Service.Users.UserService;
import com.example.demo.entity.ResetPasswordResponse;
import com.example.demo.entity.LoginResponse;
import com.example.demo.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return userService.create(user);
    }

    @GetMapping("/users/{username}")
    public User getByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    @GetMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestParam String username, @RequestParam String password) {
        User user = userService.login(username, password);
        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("01", "Invalid username or password", null));
        }

        return ResponseEntity.ok(new LoginResponse("00", "Login success", user));
    }

    @GetMapping("/resetPassword")
    public ResponseEntity<ResetPasswordResponse> resetPassword(@RequestParam String username, String email) {
        User user = userService.resetPassword(username, email);
        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ResetPasswordResponse("02", "Invalid user", null));
        }

        return ResponseEntity.ok(new ResetPasswordResponse("00", "Reset password success", user));
    }
}
