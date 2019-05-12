package com.udemy.msb.restfulstarter.services;

import com.udemy.msb.restfulstarter.domain.Post;

public interface PostService {

    Post savePost(Post post);

    Post getPostById(Long id);

    void deletePostById(Long id);
}
