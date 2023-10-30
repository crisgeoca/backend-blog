package com.solvedex.blog.mapper;

import com.solvedex.blog.domain.Comment;
import com.solvedex.blog.domain.Post;
import com.solvedex.blog.domain.User;
import com.solvedex.blog.dto.CommentDto;
import com.solvedex.blog.dto.CreateCommentDto;
import com.solvedex.blog.dto.CreatePostDto;
import com.solvedex.blog.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "createdAt", source = "comment.createdAt")
    @Mapping(target = "createdBy", source = "comment.createdBy")
    @Mapping(target = "id", source = "comment.id")
    @Mapping(target = "text", source = "comment.text")
    CommentDto toCommentDto(Comment comment);

    @Mapping(target = "text", source = "createCommentDto.text")
    @Mapping(target = "createdBy", source = "user.name")
    @Mapping(target = "updatedBy", source = "user.name")
    @Mapping(target = "user", source = "user")
    Comment toComment(CreateCommentDto createCommentDto, User user);

}
