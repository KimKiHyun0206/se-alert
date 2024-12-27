package com.se.comment.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
public class CommentWithStudentResponse {
    private Long id;
    private String writerName;
    private String comment;

    @Builder
    public CommentWithStudentResponse(Long id, String writerName, String comment) {
        this.id = id;
        this.writerName = writerName;
        this.comment = comment;
    }
}
