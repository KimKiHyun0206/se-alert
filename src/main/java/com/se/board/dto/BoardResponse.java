package com.se.board.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class BoardResponse {
    private Long id;
    private String writerId;
    private String title;
    private String content;

    @Builder
    public BoardResponse(Long id, String writerId, String title, String content) {
        this.id = id;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
    }
}
