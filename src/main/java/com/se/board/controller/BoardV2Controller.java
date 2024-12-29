package com.se.board.controller;

import com.se.board.service.BoardV2Service;
import com.se.common.dto.ResponseDto;
import com.se.common.dto.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/board")
public class BoardV2Controller {

    private final BoardV2Service boardV2Service;

    @GetMapping("/{id}")
    public ResponseEntity<?> readBoard(@PathVariable(value = "id")Long id){
        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, boardV2Service.readBoard(id));
    }
}
