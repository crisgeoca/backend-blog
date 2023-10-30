package com.solvedex.blog.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class CommentDto {

    private UUID id;

    private String text;

    private ZonedDateTime createdAt;

    private String createdBy;

}
