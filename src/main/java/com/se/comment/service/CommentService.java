package com.se.comment.service;

import com.se.comment.dto.request.CommentCreateRequest;
import com.se.comment.dto.response.CommentResponse;
import com.se.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentResponse createComment(CommentCreateRequest request, String writerId){
        return commentRepository.createComment(request, writerId).toResponse();
    }

    public CommentResponse readComment(Long commentId){
        return commentRepository.readComment(commentId).toResponse();
    }
}
