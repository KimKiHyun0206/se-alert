package com.se.common.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ResponseDto<T> {
    private final T data;
    private final String message;
    private final String serverDateTime;

    public ResponseDto(ResponseMessage message, T data) {
        this.message = message.name();
        this.serverDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.data = data;
    }

    public static <T> ResponseEntity<ResponseDto<T>> toResponseEntity(ResponseMessage message, T data) {
        return ResponseEntity
                .status(message.getStatus())
                .body(new ResponseDto<>(message, data));
    }
}