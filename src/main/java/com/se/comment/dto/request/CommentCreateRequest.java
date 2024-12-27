package com.se.comment.dto.request;

import lombok.Data;

@Data
public class CommentCreateRequest {
    private String comment;
    private Long boardId;
}
