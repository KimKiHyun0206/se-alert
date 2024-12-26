package com.se.board.dto.request;

import lombok.Data;

@Data
public class BoardSearchRequest {
    private String title;
    private String writerId;
}
