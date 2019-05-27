package com.udemy.msb.restfulstarter.services.impl;

import com.udemy.msb.restfulstarter.domain.User;
import com.udemy.msb.restfulstarter.repositories.UserRepository;
import com.udemy.msb.restfulstarter.services.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("db")
public class UserServiceDb implements UserService {

    private UserRepository userRepository;

    public UserServiceDb(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public long totalUsers() {
        return userRepository.count();
    }
}
