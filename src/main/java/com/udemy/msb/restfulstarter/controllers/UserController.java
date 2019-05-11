package com.udemy.msb.restfulstarter.controllers;

import com.udemy.msb.restfulstarter.domain.User;
import com.udemy.msb.restfulstarter.exceptions.UserNotFoundException;
import com.udemy.msb.restfulstarter.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    User getUserById(@PathVariable Long id) {
        User user = userService.findById(id);

        if(user == null) {
            throw new UserNotFoundException(String.format("User with id %s not found", id));
        }

        return user;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}