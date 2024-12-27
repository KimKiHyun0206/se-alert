package com.se.comment.dto.response;

import com.se.board.domain.Board;
import com.se.student.domain.Student;
import lombok.Builder;
import lombok.Data;

@Data
public class CommentResponse {
    private Long id;
    private String context;

    @Builder
    public CommentResponse(Long id, String context) {
        this.id = id;
        this.context = context;
    }
}
