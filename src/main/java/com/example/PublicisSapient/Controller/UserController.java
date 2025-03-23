package com.example.PublicisSapient.Controller;

import com.example.PublicisSapient.Model.User;
import com.example.PublicisSapient.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Fetch all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Fetch users by role
    @GetMapping("/role/{role}")
    public List<User> getUsersByRole(@PathVariable String role) {
        return userService.getUsersByRole(role);
    }

    // Fetch users sorted by age
    @GetMapping("/sorted")
    public List<User> getUsersSortedByAge(@RequestParam boolean ascending) {
        return userService.getUsersSortedByAge(ascending);
    }

    // Fetch user by ID or SSN
    @GetMapping("/{idOrSsn}")
    public Optional<User> getUserByIdOrSsn(@PathVariable String idOrSsn) {
        return userService.ge(
                idOrSsn.matches("\\d+") ? Long.parseLong(idOrSsn) : null, idOrSsn);
    }
}