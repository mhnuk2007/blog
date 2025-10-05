package com.learning.blog.service;

import com.learning.blog.dto.CommentResponse;
import com.learning.blog.entity.Comment;
import com.learning.blog.entity.Post;
import com.learning.blog.entity.User;
import com.learning.blog.repository.CommentRepository;
import com.learning.blog.repository.PostRepository;
import com.learning.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentResponse addComment(Long postId, String username, String content) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = Comment.builder()
                .content(content)
                .post(post)
                .author(user)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Comment saved = commentRepository.save(comment);
        return new CommentResponse(saved.getId(), saved.getContent(), user.getUsername(), saved.getCreatedAt());
    }

    public List<CommentResponse> getCommentsByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return commentRepository.findByPost(post)
                .stream()
                .map(c -> new CommentResponse(c.getId(), c.getContent(), c.getAuthor().getUsername(), c.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public CommentResponse updateComment(Long commentId, String username, String newContent) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getAuthor().getUsername().equals(username) &&
                !comment.getAuthor().getRole().name().equals("ADMIN")) {
            throw new AccessDeniedException("You are not allowed to edit this comment");
        }

        comment.setContent(newContent);
        comment.setUpdatedAt(LocalDateTime.now());
        Comment updated = commentRepository.save(comment);
        return new CommentResponse(updated.getId(), updated.getContent(), updated.getAuthor().getUsername(), updated.getCreatedAt());
    }

    public void deleteComment(Long commentId, String username) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getAuthor().getUsername().equals(username) &&
                !comment.getAuthor().getRole().name().equals("ADMIN")) {
            throw new AccessDeniedException("You are not allowed to delete this comment");
        }

        commentRepository.delete(comment);
    }
}
