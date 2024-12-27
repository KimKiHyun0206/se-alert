package com.se.board.domain;

import com.se.board.dto.response.BoardResponse;
import com.se.comment.domain.Comment;
import com.se.common.BaseEntity;
import com.se.student.domain.Student;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    private String title;
    private String content;

    @Enumerated
    private BoardCategory boardCategory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Board(Student student, String title, String content, BoardCategory boardCategory, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.student = student;
        this.title = title;
        this.content = content;
        this.boardCategory = boardCategory;
        super.createdAt = createdAt;
        super.modifiedAt = modifiedAt;
    }

    public BoardResponse toResponse() {
        return BoardResponse.builder()
                .id(id)
                .student(student)
                .title(title)
                .content(content)
                .category(boardCategory)
                .comments(comments)
                .build();
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
