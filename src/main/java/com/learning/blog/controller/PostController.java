package com.learning.blog.controller;

import com.learning.blog.dto.PostResponse;
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
    public ResponseEntity<PostResponse> createPost(
            @RequestParam String title,
            @RequestParam String content,
            Authentication authentication) {
        PostResponse response = postService.createPost(title, content, authentication.getName());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<PostResponse>> getPostsByUser(@PathVariable String username) {
        return new ResponseEntity<>(postService.getPostsByUser(username), HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable Long postId,
            @RequestParam String title,
            @RequestParam String content,
            Authentication authentication) {
        PostResponse response = postService.updatePost(postId, authentication.getName(), title, content);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long postId,
            Authentication authentication) {
        postService.deletePost(postId, authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
