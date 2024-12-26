package com.se.board.dto.response;

import com.se.board.domain.BoardCategory;
import com.se.student.domain.Student;
import lombok.Builder;
import lombok.Data;

@Data
public class BoardWithStudentResponse {
    private Long id;
    private Student student;
    private String title;
    private String content;
    private BoardCategory category;

    @Builder
    public BoardWithStudentResponse(Long id, Student student, String title, String content, BoardCategory category) {
        this.id = id;
        this.student = student;
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
