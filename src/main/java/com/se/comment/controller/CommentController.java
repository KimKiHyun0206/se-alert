package com.se.comment.controller;

import com.se.comment.dto.request.CommentCreateRequest;
import com.se.comment.dto.response.CommentResponse;
import com.se.comment.service.CommentService;
import com.se.common.dto.ResponseDto;
import com.se.common.dto.ResponseMessage;
import com.se.util.TokenResolveUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentCreateRequest request, HttpServletRequest httpServletRequest){
        String writerId = TokenResolveUtil.resolveTokenAndGetUserId(httpServletRequest);
        log.info(request.getComment());
        CommentResponse comment = commentService.createComment(request, writerId);
        return ResponseDto.toResponseEntity(ResponseMessage.COMMENT_CREATE_SUCCESS, comment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getComment(@PathVariable(value = "id") Long id){
        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, commentService.readComment(id));
    }
}
