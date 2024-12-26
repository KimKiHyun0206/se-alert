package com.se.board.service;

import com.se.board.dto.BoardCreateRequest;
import com.se.board.dto.BoardResponse;
import com.se.board.dto.BoardUpdateRequest;
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

    public BoardResponse update(BoardUpdateRequest request, String writerId) {
        return boardRepository.update(request, writerId);
    }

    public long delete(Long id, String writerId) {
        return boardRepository.delete(id, writerId);
    }
}
