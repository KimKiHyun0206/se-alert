package com.se.board.dto;

import com.se.board.domain.Board;
import lombok.Data;

@Data
public class BoardCreateRequest {
    private String title;
    private String context;
}
