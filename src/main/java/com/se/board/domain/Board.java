package com.se.board.domain;

import com.se.board.dto.BoardResponse;
import com.se.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

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
    public Board(String writerId, String title, String content, BoardCategory boardCategory) {
        this.writerId = writerId;
        this.title = title;
        this.content = content;
        this.boardCategory = boardCategory;
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
