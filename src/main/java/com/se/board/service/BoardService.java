package com.se.board.service;

import com.se.board.domain.Board;
import com.se.board.dto.request.BoardCreateRequest;
import com.se.board.dto.response.BoardResponse;
import com.se.board.dto.request.BoardSearchRequest;
import com.se.board.dto.request.BoardUpdateRequest;
import com.se.board.repository.BoardRepository;
import com.se.comment.domain.Comment;
import com.se.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResponse create(BoardCreateRequest request, String writerId) {
        return boardRepository.create(request, writerId).toResponse();
    }

    public BoardResponse read(Long id) {
        BoardResponse boardResponse = boardRepository.readById(id).toResponse();
        for (Comment comment : boardResponse.getComments()) {
            comment.getCreatedAt();
        }
        return boardResponse;
    }

    public BoardResponse readWithStudent(Long id) {
        return boardRepository.readById(id).toResponse();
    }

    public List<BoardResponse> readAll() {
        return boardRepository.readAll().stream().map(Board::toResponse).toList();
    }

    public List<BoardResponse> readAllByCondition(BoardSearchRequest request) {
        return boardRepository.readAllByCondition(request).stream().map(Board::toResponse).toList();
    }

    public BoardResponse update(BoardUpdateRequest request, String writerId) {
        return boardRepository.update(request, writerId).toResponse();
    }

    public boolean delete(Long id, String writerId) {
        return boardRepository.delete(id, writerId) != 0;
    }
}
