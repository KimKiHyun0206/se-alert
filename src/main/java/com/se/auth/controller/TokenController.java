package com.se.auth.controller;

import com.se.common.dto.ResponseDto;
import com.se.common.dto.ResponseMessage;
import com.se.util.TokenResolveUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/token")
public class TokenController {

    @GetMapping
    public ResponseEntity<?> getTokenName(@RequestParam("token") String token) {
        log.info("TOKEN VALUE {}",token);
        Authentication authentication = TokenResolveUtil.getAuthentication(token);
        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, authentication);
    }
}
