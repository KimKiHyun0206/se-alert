package com.se.board.service;

import com.se.board.domain.Board;
import com.se.board.dto.request.BoardCreateRequest;
import com.se.board.dto.response.BoardResponse;
import com.se.board.dto.request.BoardSearchRequest;
import com.se.board.dto.request.BoardUpdateRequest;
import com.se.board.repository.FullBoardRepository;
import com.se.comment.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final FullBoardRepository fullBoardRepository;

    public BoardResponse create(BoardCreateRequest request, String writerId) {
        return fullBoardRepository.create(request, writerId).toResponse();
    }

    public BoardResponse read(Long id) {
        BoardResponse boardResponse = fullBoardRepository.readById(id).toResponse();
        for (Comment comment : boardResponse.getComments()) {
            comment.getCreatedAt();
        }
        return boardResponse;
    }

    public BoardResponse readWithStudent(Long id) {
        return fullBoardRepository.readById(id).toResponse();
    }

    public List<BoardResponse> readAll() {
        return fullBoardRepository.readAll().stream().map(Board::toResponse).toList();
    }

    public List<BoardResponse> readAllByCondition(BoardSearchRequest request) {
        return fullBoardRepository.readAllByCondition(request).stream().map(Board::toResponse).toList();
    }

    public BoardResponse update(BoardUpdateRequest request, String writerId) {
        return fullBoardRepository.update(request, writerId).toResponse();
    }

    public boolean delete(Long id, String writerId) {
        return fullBoardRepository.delete(id, writerId) != 0;
    }
}
