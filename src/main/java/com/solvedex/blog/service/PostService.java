package com.solvedex.blog.service;

import com.solvedex.blog.domain.Post;
import com.solvedex.blog.domain.User;
import com.solvedex.blog.dto.CreatePostDto;
import com.solvedex.blog.dto.PostDto;
import com.solvedex.blog.mapper.PostMapper;
import com.solvedex.blog.repository.PostRepository;
import com.solvedex.blog.service.interfaces.PostInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService implements PostInterface {

    private static final PostMapper MAPPER = PostMapper.INSTANCE;

    private final PostRepository postRepository;

    @Override
    public PostDto createPost(User user, CreatePostDto createPostDto) {

        return MAPPER.toPostDto(postRepository.save(MAPPER.toPost(createPostDto, user)));
    }

    @Override
    public List<PostDto> postsList() {

        return postRepository.findAll().stream().map(MAPPER::toPostDto).toList();
    }

    @Override
    public PostDto getPostById(UUID postId) {
        return MAPPER.toPostDto(postRepository.findById(postId).orElseThrow());
    }

    @Override
    public PostDto updatePost(User user, CreatePostDto createPostDto, UUID postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        post.setText(createPostDto.getText());
        post.setUpdatedBy(user.getName());
        return MAPPER.toPostDto(postRepository.save(post));
    }

    @Override
    public void deletePost(UUID commentId) {
        postRepository.deleteById(commentId);
    }

}
