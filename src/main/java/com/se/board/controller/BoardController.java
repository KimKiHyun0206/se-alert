package com.se.board.controller;

import com.se.board.dto.request.BoardCreateRequest;
import com.se.board.dto.response.FullBoardResponse;
import com.se.board.dto.request.BoardSearchRequest;
import com.se.board.dto.request.BoardUpdateRequest;
import com.se.board.service.BoardService;
import com.se.common.dto.ResponseDto;
import com.se.common.dto.ResponseMessage;
import com.se.error.exception.board.BoardUpdateFailException;
import com.se.util.TokenResolveUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody BoardCreateRequest request, HttpServletRequest httpServletRequest) {
        String id = TokenResolveUtil.resolveTokenAndGetUserId(httpServletRequest);
        log.info(id);
        return ResponseDto.toResponseEntity(ResponseMessage.BOARD_CREATE_SUCCESS, boardService.create(request, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readBoard(@PathVariable(value = "id") Long id, @RequestParam(value = "isWithStudent") boolean isWithStudent) {
        if (isWithStudent) {
            FullBoardResponse fullBoardResponse = boardService.readWithStudent(id);
            return fullBoardResponse != null ?
                    ResponseDto.toResponseEntity(ResponseMessage.BOARD_READ_SUCCESS, fullBoardResponse) :
                    ResponseDto.toResponseEntity(ResponseMessage.BOARD_READ_FAIL, null);
        }
        FullBoardResponse read = boardService.read(id);
        return read != null ? ResponseDto.toResponseEntity(ResponseMessage.BOARD_READ_SUCCESS, read) : ResponseDto.toResponseEntity(ResponseMessage.BOARD_READ_FAIL, null);
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
        FullBoardResponse update = boardService.update(request, id);
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
