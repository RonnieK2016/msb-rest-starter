package com.udemy.msb.restfulstarter.repositories;

import com.udemy.msb.restfulstarter.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
