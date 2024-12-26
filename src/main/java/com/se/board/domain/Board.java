package com.se.board.domain;

import com.se.board.dto.BoardResponse;
import com.se.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Builder
    public Board(String writerId, String title, String content) {
        this.writerId = writerId;
        this.title = title;
        this.content = content;
    }

    public BoardResponse toResponse() {
        return BoardResponse.builder()
                .id(id)
                .writerId(writerId)
                .title(title)
                .content(content)
                .build();
    }
}
