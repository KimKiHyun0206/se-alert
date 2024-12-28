package com.se.notice.dto;

import com.se.student.domain.Student;
import lombok.Builder;
import lombok.Data;

@Data
public class NoticeResponse {
    private Long id;
    private String title;
    private String content;
    private Student student;
    private Long receiverPermission;

    @Builder
    public NoticeResponse(Long id, String title, String content, Student student, Long receiverPermission) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.student = student;
        this.receiverPermission = receiverPermission;
    }
}
