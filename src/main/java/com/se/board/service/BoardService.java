package com.se.board.service;

import com.se.board.dto.request.BoardCreateRequest;
import com.se.board.dto.BoardResponse;
import com.se.board.dto.request.BoardSearchRequest;
import com.se.board.dto.request.BoardUpdateRequest;
import com.se.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResponse create(BoardCreateRequest request, String writerId) {
        return boardRepository.create(request, writerId);
    }

    public BoardResponse read(Long id) {
        return boardRepository.readById(id);
    }

    public List<BoardResponse> readAll() {
        return boardRepository.readAll();
    }

    public List<BoardResponse> readAllByCondition(BoardSearchRequest request) {
        return boardRepository.readAllByCondition(request);
    }

    public BoardResponse update(BoardUpdateRequest request, String writerId) {
        return boardRepository.update(request, writerId);
    }

    public boolean delete(Long id, String writerId) {
        return boardRepository.delete(id, writerId) != 0;
    }
}
