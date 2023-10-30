package com.solvedex.blog.service.interfaces;

import com.solvedex.blog.domain.User;
import com.solvedex.blog.dto.CommentDto;
import com.solvedex.blog.dto.CreateCommentDto;

import java.util.List;
import java.util.UUID;

public interface CommentInterface {

    CommentDto createComment(User user, CreateCommentDto createCommentDto);

    List<CommentDto> commentsList(UUID postId);

    CommentDto getCommentById(UUID commentId);

    CommentDto updateComment(User user, CreateCommentDto createCommentDto, UUID commentId);

    void deleteComment(UUID commentId);

}
