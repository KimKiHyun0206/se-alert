package com.se.board.dto.response;

import com.se.board.domain.BoardCategory;
import com.se.comment.domain.Comment;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DefaultBoardResponse {
    private String title;
    private String userName;
    private String content;
    private BoardCategory boardCategory;
    private List<Comment> comments;

    @Builder
    public DefaultBoardResponse(String title, String userName, String content, BoardCategory boardCategory, List<Comment> comments) {
        this.title = title;
        this.userName = userName;
        this.content = content;
        this.boardCategory = boardCategory;
        this.comments = comments;
    }
}
