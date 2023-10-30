package com.solvedex.blog.service;

import com.solvedex.blog.domain.Comment;
import com.solvedex.blog.domain.User;
import com.solvedex.blog.dto.CommentDto;
import com.solvedex.blog.dto.CreateCommentDto;
import com.solvedex.blog.mapper.CommentMapper;
import com.solvedex.blog.repository.CommentRepository;
import com.solvedex.blog.repository.PostRepository;
import com.solvedex.blog.service.interfaces.CommentInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService implements CommentInterface {

    private static final CommentMapper MAPPER = CommentMapper.INSTANCE;

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    @Override
    public CommentDto createComment(User user, CreateCommentDto createCommentDto) {

        Comment comment = MAPPER.toComment(createCommentDto, user);
        comment.setPost(postRepository.findById(createCommentDto.getPostId()).orElseThrow());
        return MAPPER.toCommentDto(commentRepository.save(comment));
    }

    @Override
    public List<CommentDto> commentsList(UUID postId) {
        return commentRepository.findByPostId(postId).stream().map(MAPPER::toCommentDto).toList();
    }

    @Override
    public CommentDto getCommentById(UUID commentId) {
        return MAPPER.toCommentDto(commentRepository.findById(commentId).orElseThrow());
    }

    @Override
    public CommentDto updateComment(User user, CreateCommentDto createCommentDto, UUID commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        comment.setText(createCommentDto.getText());
        comment.setUpdatedBy(user.getName());
        return MAPPER.toCommentDto(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(UUID commentId) {
        commentRepository.deleteById(commentId);
    }

}
