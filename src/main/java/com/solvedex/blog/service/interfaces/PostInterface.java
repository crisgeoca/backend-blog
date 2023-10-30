package com.solvedex.blog.service.interfaces;

import com.solvedex.blog.domain.User;
import com.solvedex.blog.dto.CreatePostDto;
import com.solvedex.blog.dto.PostDto;

import java.util.List;
import java.util.UUID;

public interface PostInterface {

    PostDto createPost(User user, CreatePostDto createPostDto);

    List<PostDto> postsList();

    PostDto getPostById(UUID postId);

    PostDto updatePost(User user, CreatePostDto createPostDto, UUID postId);

    void deletePost(UUID commentId);

}
