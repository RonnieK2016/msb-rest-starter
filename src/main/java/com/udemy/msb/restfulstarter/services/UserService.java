package com.udemy.msb.restfulstarter.services;

import com.udemy.msb.restfulstarter.domain.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User saveUser(User user);

    User findById(Long id);

    void deleteUserById(Long id);

    long totalUsers();
}
