package com.solvedex.blog.mapper;

import com.solvedex.blog.domain.Post;
import com.solvedex.blog.domain.User;
import com.solvedex.blog.dto.CreatePostDto;
import com.solvedex.blog.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "createdAt", source = "post.createdAt")
    @Mapping(target = "createdBy", source = "post.createdBy")
    @Mapping(target = "id", source = "post.id")
    @Mapping(target = "text", source = "post.text")
    PostDto toPostDto(Post post);

    @Mapping(target = "text", source = "createPostDto.text")
    @Mapping(target = "createdBy", source = "user.name")
    @Mapping(target = "updatedBy", source = "user.name")
    @Mapping(target = "user", source = "user")
    Post toPost(CreatePostDto createPostDto, User user);
}
