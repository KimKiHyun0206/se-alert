package com.se.board.service;

import com.se.board.dto.request.BoardCreateRequest;
import com.se.board.dto.response.BoardResponse;
import com.se.board.dto.request.BoardSearchRequest;
import com.se.board.dto.request.BoardUpdateRequest;
import com.se.board.dto.response.BoardWithStudentResponse;
import com.se.board.repository.BoardRepository;
import com.se.student.dto.response.StudentResponse;
import com.se.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final StudentRepository studentRepository;

    public BoardResponse create(BoardCreateRequest request, String writerId) {
        return boardRepository.create(request, writerId);
    }

    public BoardResponse read(Long id) {
        return boardRepository.readById(id);
    }

    public BoardWithStudentResponse readWithStudent(Long id) {
        BoardResponse boardResponse = boardRepository.readById(id);
        StudentResponse studentResponse = studentRepository.readById(boardResponse.getWriterId());
        return BoardWithStudentResponse
                .builder()
                .id(boardResponse.getId())
                .title(boardResponse.getTitle())
                .category(boardResponse.getCategory())
                .content(boardResponse.getContent())
                .studentName(studentResponse.getName())
                .build();
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
