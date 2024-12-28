package com.se.notice.domain;

import com.se.common.BaseEntity;
import com.se.notice.dto.NoticeResponse;
import com.se.student.domain.Student;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;
    private String title;
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    private Long receiverPermission;

    @Builder
    public Notice(String title, String content, Student student, Long receiverPermission, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.title = title;
        this.content = content;
        this.student = student;
        this.receiverPermission = receiverPermission;
        super.createdAt = createdAt;
        super.modifiedAt = modifiedAt;
    }


    public NoticeResponse toResponse() {
        return NoticeResponse.builder()
                .id(id)
                .title(title)
                .content(content)
                .student(student)
                .receiverPermission(receiverPermission)
                .build();
    }
}
