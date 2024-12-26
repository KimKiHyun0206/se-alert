package com.se.board.dto;

import com.se.board.domain.BoardCategory;
import lombok.Builder;
import lombok.Data;

@Data
public class BoardResponse {
    private Long id;
    private String writerId;
    private String title;
    private String content;
    private BoardCategory category;

    @Builder
    public BoardResponse(Long id, String writerId, String title, String content, BoardCategory category) {
        this.id = id;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
