package com.udemy.msb.restfulstarter.controllers;

import com.udemy.msb.restfulstarter.domain.Post;
import com.udemy.msb.restfulstarter.domain.User;
import com.udemy.msb.restfulstarter.exceptions.UserNotFoundException;
import com.udemy.msb.restfulstarter.services.PostService;
import com.udemy.msb.restfulstarter.services.UserService;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private PostService postService;

    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/")
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    Resource<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);

        if(user == null) {
            throw new UserNotFoundException(String.format("User with id %s not found", id));
        }

        Resource<User> resource = new Resource<>(user);
        ControllerLinkBuilder controllerLinkBuilder = ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllUsers());
        resource.add(controllerLinkBuilder.withRel("all-users"));

        return resource;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    User createUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/{userId}/posts")
    List<Post> getAllPosts(@PathVariable Long userId) {

        User user = userService.findById(userId);

        if(user == null) {
            throw new UserNotFoundException(String.format("User with id %s not found", userId));
        }

        return user.getPosts();
    }

    @GetMapping("/{userId}/posts/{postId}")
    Post getPostById(@PathVariable Long userId, @PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PostMapping("/{userId}/posts")
    Post createPost(@PathVariable Long userId, @RequestBody Post post) {
        User user = userService.findById(userId);

        if(user == null) {
            throw new UserNotFoundException(String.format("User with id %s not found", userId));
        }

        post.setUser(user);

        Post savedPost = postService.savePost(post);

        user.addPost(savedPost);

        userService.saveUser(user);

        return savedPost;
    }

    @DeleteMapping("/{userId}/posts/{postId}")
    void deletePost(@PathVariable Long userId, @PathVariable Long postId) {
        User user = userService.findById(userId);

        if(user == null) {
            throw new UserNotFoundException(String.format("User with id %s not found", userId));
        }

        user.getPosts().removeIf(post -> post.getId().equals(postId));

        postService.deletePostById(postId);
    }
}
