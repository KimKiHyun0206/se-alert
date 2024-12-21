package com.se.jwt.dto;

import lombok.Data;

@Data
public class TokenDto {
    private final String accessToken;
    private final String refreshToken;
}
