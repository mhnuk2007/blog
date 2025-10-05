package com.learning.blog.service;

import com.learning.blog.dto.PostResponse;
import com.learning.blog.entity.Post;
import com.learning.blog.entity.User;
import com.learning.blog.repository.PostRepository;
import com.learning.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostResponse createPost(String title, String content, String username) {
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = Post.builder()
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .author(author)
                .build();

        return mapToResponse(postRepository.save(post));
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<PostResponse> getPostsByUser(String username) {
        return postRepository.findByAuthorUsername(username)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public PostResponse updatePost(Long postId, String username, String title, String content) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("You are not allowed to update this post");
        }

        post.setTitle(title);
        post.setContent(content);
        post.setUpdatedAt(LocalDateTime.now());

        return mapToResponse(postRepository.save(post));
    }

    public void deletePost(Long postId, String username) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("You are not allowed to delete this post");
        }

        postRepository.delete(post);
    }

    private PostResponse mapToResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .authorUsername(post.getAuthor().getUsername())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
