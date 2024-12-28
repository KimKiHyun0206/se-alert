package com.se.jwt.service;

import com.se.jwt.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenProvider tokenProvider;
}
