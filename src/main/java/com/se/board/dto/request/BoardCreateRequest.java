package com.se.board.dto.request;

import com.se.board.domain.BoardCategory;
import lombok.Data;

@Data
public class BoardCreateRequest {
    private String title;
    private String context;
    private BoardCategory category;
}
