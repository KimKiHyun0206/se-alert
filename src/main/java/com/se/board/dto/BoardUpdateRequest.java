package com.se.board.dto;

import lombok.Data;

@Data
public class BoardUpdateRequest {
    private Long id;
    private String title;
    private String context;
}
