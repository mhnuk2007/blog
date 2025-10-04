package com.learning.blog.controller;

import com.learning.blog.entity.Post;
import com.learning.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestParam String title,
                                           @RequestParam String content,
                                           Authentication authentication) {
        Post createdPost = postService.createPost(title, content, authentication.getName());
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable String username) {
        return new ResponseEntity<>(postService.getPostsByUser(username), HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId,
                                           @RequestParam String title,
                                           @RequestParam String content,
                                           Authentication authentication) {
        Post updatedPost = postService.updatePost(postId, authentication.getName(), title, content);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId,
                                             Authentication authentication) {
        postService.deletePost(postId, authentication.getName());
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.NO_CONTENT);
    }
}
