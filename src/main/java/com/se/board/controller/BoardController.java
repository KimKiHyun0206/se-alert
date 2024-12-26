package com.se.board.controller;

import com.se.board.dto.request.BoardCreateRequest;
import com.se.board.dto.BoardResponse;
import com.se.board.dto.request.BoardSearchRequest;
import com.se.board.dto.request.BoardUpdateRequest;
import com.se.board.service.BoardService;
import com.se.common.dto.ResponseDto;
import com.se.common.dto.ResponseMessage;
import com.se.util.TokenResolveUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody BoardCreateRequest request, HttpServletRequest httpServletRequest) {
        String id = TokenResolveUtil.resolveTokenAndGetUserId(httpServletRequest);
        return ResponseDto.toResponseEntity(ResponseMessage.BOARD_CREATE_SUCCESS, boardService.create(request, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readBoard(@PathVariable(value = "id") Long id) {
        return ResponseDto.toResponseEntity(ResponseMessage.BOARD_READ_SUCCESS, boardService.read(id));
    }

    @GetMapping
    public ResponseEntity<?> readAllBoard() {
        return ResponseDto.toResponseEntity(ResponseMessage.BOARD_READ_ALL_SUCCESS, boardService.readAll());
    }

    @GetMapping("/search")
    public ResponseEntity<?> readBoardByCondition(@RequestBody BoardSearchRequest request) {
        return ResponseDto.toResponseEntity(ResponseMessage.BOARD_READ_ALL_CONDITION_SUCCESS, boardService.readAllByCondition(request));
    }

    @PatchMapping
    public ResponseEntity<?> updateBoard(@RequestBody BoardUpdateRequest request, HttpServletRequest httpServletRequest) {
        String id = TokenResolveUtil.resolveTokenAndGetUserId(httpServletRequest);
        BoardResponse update = boardService.update(request, id);
        if (update == null) {
            throw new BoardUpdateFailException();
        }
        return ResponseDto.toResponseEntity(ResponseMessage.BOARD_UPDATE_SUCCESS, update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(HttpServletRequest httpServletRequest, @PathVariable(value = "id") Long id) {
        String writerId = TokenResolveUtil.resolveTokenAndGetUserId(httpServletRequest);
        if (boardService.delete(id, writerId)) {
            return ResponseDto.toResponseEntity(ResponseMessage.BOARD_DELETE_SUCCESS, null);
        }
        return ResponseDto.toResponseEntity(ResponseMessage.BOARD_DELETE_FAIL, null);
    }
}
