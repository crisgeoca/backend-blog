package com.solvedex.blog.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateCommentDto {

    private String text;

    private UUID postId;

}
