package com.se.comment.domain;

import com.se.board.domain.Board;
import com.se.comment.dto.response.CommentResponse;
import com.se.comment.dto.response.DefaultCommentResponse;
import com.se.common.BaseEntity;
import com.se.student.domain.Student;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;
    private String context;

    @Builder
    public Comment(Long id, Student student, String context, LocalDateTime createdAt, Board board) {
        this.id = id;
        this.student = student;
        this.board = board;
        this.context = context;
        super.createdAt = createdAt;
    }

    public CommentResponse toResponse() {
        return CommentResponse.builder()
                .id(id)
                .context(context)
                .build();
    }

    public DefaultCommentResponse toDefaultResponse() {
        return DefaultCommentResponse
                .builder()
                .comment(context)
                .writerName(student.getName().getName())
                .createdAt(super.createdAt)
                .isModified(super.modifiedAt != null)
                .build();
    }
}
