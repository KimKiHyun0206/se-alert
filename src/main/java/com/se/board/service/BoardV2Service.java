package com.se.board.service;

import com.se.board.domain.Board;
import com.se.board.dto.request.BoardCreateRequest;
import com.se.board.dto.response.BoardListResponse;
import com.se.board.dto.response.DefaultBoardResponse;
import com.se.board.dto.response.FullBoardResponse;
import com.se.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardV2Service {
    private final BoardRepository boardRepository;

    public DefaultBoardResponse readBoard(Long boardId) {
        return boardRepository.readById(boardId).toDefaultResponse();
    }

    public List<BoardListResponse> readAll() {
        return boardRepository.readAll().stream().map(Board::toListResponse).toList();
    }
}
