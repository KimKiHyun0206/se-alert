package com.se.board.dto.response;

import com.se.board.domain.BoardCategory;
import com.se.student.domain.Student;
import lombok.Builder;
import lombok.Data;

@Data
public class BoardWithStudentResponse {
    private Long id;
    private String studentName;
    private String title;
    private String content;
    private BoardCategory category;

    @Builder
    public BoardWithStudentResponse(Long id, String studentName, String title, String content, BoardCategory category) {
        this.id = id;
        this.studentName = studentName;
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
