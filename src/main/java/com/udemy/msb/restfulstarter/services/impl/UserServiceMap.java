package com.udemy.msb.restfulstarter.services.impl;

import com.udemy.msb.restfulstarter.domain.User;
import com.udemy.msb.restfulstarter.services.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Profile("map")
public class UserServiceMap implements UserService {

    private HashMap<Long, User> storage = new HashMap<>();

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public User saveUser(User user) {
        user.setId((long) (storage.size() + 1));
        storage.put(user.getId(), user);

        return user;
    }

    @Override
    public User findById(Long id) {
        return storage.get(id);
    }

    @Override
    public int totalUsers() {
        return storage.size();
    }
}
