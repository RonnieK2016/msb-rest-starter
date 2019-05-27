package com.udemy.msb.restfulstarter.services.impl;

import com.udemy.msb.restfulstarter.domain.Post;
import com.udemy.msb.restfulstarter.repositories.PostRepository;
import com.udemy.msb.restfulstarter.services.PostService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("db")
public class PostServiceDb implements PostService {

    private PostRepository postRepository;

    public PostServiceDb(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }
}
