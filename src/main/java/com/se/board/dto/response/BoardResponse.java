package com.se.board.dto.response;

import com.se.board.domain.BoardCategory;
import com.se.comment.domain.Comment;
import com.se.student.domain.Student;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class BoardResponse {
    private Long id;
    private Student student;
    private String title;
    private String content;
    private BoardCategory category;
    private List<Comment> comments;

    @Builder
    public BoardResponse(Long id, Student student, String title, String content, BoardCategory category, List<Comment> comments) {
        this.id = id;
        this.student = student;
        this.title = title;
        this.content = content;
        this.category = category;
        this.comments = comments;
    }
}
