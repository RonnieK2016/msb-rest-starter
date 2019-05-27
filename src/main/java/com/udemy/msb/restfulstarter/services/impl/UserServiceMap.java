package com.udemy.msb.restfulstarter.services.impl;

import com.udemy.msb.restfulstarter.domain.User;
import com.udemy.msb.restfulstarter.services.PostService;
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
    private PostService postService;

    public UserServiceMap(PostService postService) {
        this.postService = postService;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public User saveUser(User user) {
        if(user.getId() == null) {
            user.setId((long) (storage.size() + 1));
        }

        user.getPosts().stream().filter(post -> post.getId() == null).forEach(postService::savePost);

        storage.put(user.getId(), user);

        return user;
    }

    @Override
    public User findById(Long id) {
        return storage.get(id);
    }

    @Override
    public void deleteUserById(Long id) {
        User curUser = storage.get(id);

        if(curUser != null) {
            curUser.getPosts().forEach(post -> postService.deletePostById(post.getId()));
            storage.remove(id);
        }
    }

    @Override
    public long totalUsers() {
        return storage.size();
    }
}
