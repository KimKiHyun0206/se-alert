package com.se.board.service;

import com.se.board.domain.Board;
import com.se.board.dto.request.BoardCreateRequest;
import com.se.board.dto.response.FullBoardResponse;
import com.se.board.dto.request.BoardSearchRequest;
import com.se.board.dto.request.BoardUpdateRequest;
import com.se.board.repository.BoardRepository;
import com.se.comment.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public FullBoardResponse create(BoardCreateRequest request, String writerId) {
        return boardRepository.create(request, writerId).toResponse();
    }

    public FullBoardResponse read(Long id) {
        FullBoardResponse fullBoardResponse = boardRepository.readById(id).toResponse();
        for (Comment comment : fullBoardResponse.getComments()) {
            comment.getCreatedAt();
        }
        return fullBoardResponse;
    }

    public FullBoardResponse readWithStudent(Long id) {
        return boardRepository.readById(id).toResponse();
    }

    public List<FullBoardResponse> readAll() {
        return boardRepository.readAll().stream().map(Board::toResponse).toList();
    }

    public List<FullBoardResponse> readAllByCondition(BoardSearchRequest request) {
        return boardRepository.readAllByCondition(request).stream().map(Board::toResponse).toList();
    }

    public FullBoardResponse update(BoardUpdateRequest request, String writerId) {
        return boardRepository.update(request, writerId).toResponse();
    }

    public boolean delete(Long id, String writerId) {
        return boardRepository.delete(id, writerId) != 0;
    }
}
