package com.solvedex.blog.controller.postcontroller;

import com.solvedex.blog.domain.User;
import com.solvedex.blog.dto.CreatePostDto;
import com.solvedex.blog.dto.PostDto;
import com.solvedex.blog.service.interfaces.PostInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostInterface postInterface;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@AuthenticationPrincipal User user, @RequestBody CreatePostDto createPostDto) {
        return ResponseEntity.ok(postInterface.createPost(user, createPostDto));
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(postInterface.postsList());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@AuthenticationPrincipal User user, @PathVariable UUID postId) {
        return ResponseEntity.ok(postInterface.getPostById(postId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePostById(@AuthenticationPrincipal User user, @RequestBody CreatePostDto createPostDto, @PathVariable UUID postId) {
        return ResponseEntity.ok(postInterface.updatePost(user, createPostDto, postId));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<UUID> deletePostById(@AuthenticationPrincipal User user, @PathVariable UUID postId) {
        postInterface.deletePost(postId);
        return ResponseEntity.ok(postId);
    }
}
