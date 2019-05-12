package com.udemy.msb.restfulstarter.services.impl;

import com.udemy.msb.restfulstarter.domain.Post;
import com.udemy.msb.restfulstarter.services.PostService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Profile("map")
public class PostServiceMap implements PostService {

    Map<Long, Post> storage = new HashMap<>();


    @Override
    public Post savePost(Post post) {
        if(post.getId() == null) {
            post.setId((long)storage.size() + 1);
        }
        storage.put(post.getId(), post);

        return post;
    }

    @Override
    public Post getPostById(Long id) {
        return storage.get(id);
    }

    @Override
    public void deletePostById(Long id) {
        storage.remove(id);
    }
}
