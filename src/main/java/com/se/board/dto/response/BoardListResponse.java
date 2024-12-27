package com.se.board.dto.response;

import com.se.board.domain.BoardCategory;
import lombok.Builder;
import lombok.Data;

@Data
public class BoardListResponse {
    private String title;
    private String content;
    private BoardCategory boardCategory;

    @Builder
    public BoardListResponse(String title, String content, BoardCategory boardCategory) {
        this.title = title;
        this.content = content;
        this.boardCategory = boardCategory;
    }
}
