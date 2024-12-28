package com.se.comment.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DefaultCommentResponse {
    private String writerName;
    private String comment;
    private LocalDateTime createdAt;
    private Boolean isModified;

    @Builder
    public DefaultCommentResponse(String writerName, String comment, LocalDateTime createdAt, Boolean isModified) {
        this.writerName = writerName;
        this.comment = comment;
        this.createdAt = createdAt;
        this.isModified = isModified;
    }
}
