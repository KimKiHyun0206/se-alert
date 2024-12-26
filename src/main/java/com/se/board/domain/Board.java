package com.se.board.domain;

import com.se.board.dto.response.BoardResponse;
import com.se.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String writerId;
    private String title;
    private String content;

    @Enumerated
    private BoardCategory boardCategory;

    @Builder
    public Board(String writerId, String title, String content, BoardCategory boardCategory, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.writerId = writerId;
        this.title = title;
        this.content = content;
        this.boardCategory = boardCategory;
        super.createdAt = createdAt;
        super.modifiedAt = modifiedAt;
    }

    public BoardResponse toResponse() {
        return BoardResponse.builder()
                .id(id)
                .writerId(writerId)
                .title(title)
                .content(content)
                .category(boardCategory)
                .build();
    }
}
