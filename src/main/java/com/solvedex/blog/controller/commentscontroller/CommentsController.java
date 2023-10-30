package com.solvedex.blog.controller.commentscontroller;

import com.solvedex.blog.domain.User;
import com.solvedex.blog.dto.CommentDto;
import com.solvedex.blog.dto.CreateCommentDto;
import com.solvedex.blog.service.interfaces.CommentInterface;
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
@RequestMapping(path = "/api/comments")
@RequiredArgsConstructor
public class CommentsController {

    private final CommentInterface commentInterface;

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@AuthenticationPrincipal User user, @RequestBody CreateCommentDto createCommentDto) {
        return ResponseEntity.ok(commentInterface.createComment(user, createCommentDto));
    }

    @GetMapping("/{postId}/all")
    public ResponseEntity<List<CommentDto>> getAllComments(@AuthenticationPrincipal User user, @PathVariable UUID postId) {
        return ResponseEntity.ok(commentInterface.commentsList(postId));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@AuthenticationPrincipal User user, @PathVariable UUID commentId) {
        return ResponseEntity.ok(commentInterface.getCommentById(commentId));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateCommentById(@AuthenticationPrincipal User user, @RequestBody CreateCommentDto createCommentDto, @PathVariable UUID commentId) {
        return ResponseEntity.ok(commentInterface.updateComment(user, createCommentDto, commentId));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<UUID> deleteCommentById(@AuthenticationPrincipal User user, @PathVariable UUID commentId) {
        commentInterface.deleteComment(commentId);
        return ResponseEntity.ok(commentId);
    }

}
