package com.se.board.domain;

import com.se.board.dto.response.BoardListResponse;
import com.se.board.dto.response.DefaultBoardResponse;
import com.se.board.dto.response.FullBoardResponse;
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

    public FullBoardResponse toResponse() {
        return FullBoardResponse.builder()
                .id(id)
                .title(title)
                .content(content)
                .category(boardCategory)
                .student(student)
                .comments(comments)
                .build();
    }

    public DefaultBoardResponse toDefaultResponse(){
        return DefaultBoardResponse
                .builder()
                .title(title)
                .content(content)
                .userName(student.getName().getName())
                .comments(comments)
                .build();
    }

    public BoardListResponse toListResponse(){
        return BoardListResponse.builder()
                .title(title)
                .content(content)
                .boardCategory(boardCategory)
                .build();
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
