package com.se.comment.controller;

import com.se.comment.dto.request.CommentCreateRequest;
import com.se.comment.dto.request.CommentUpdateRequest;
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
    public ResponseEntity<?> createComment(@RequestBody CommentCreateRequest request, HttpServletRequest httpServletRequest) {
        String writerId = TokenResolveUtil.resolveTokenAndGetUserId(httpServletRequest);
        return ResponseDto.toResponseEntity(ResponseMessage.COMMENT_CREATE_SUCCESS, commentService.createComment(request, writerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readComment(@PathVariable(value = "id") Long id) {
        return ResponseDto.toResponseEntity(ResponseMessage.COMMENT_READ_SUCCESS, commentService.readComment(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateComment(
            @PathVariable(value = "id") Long id,
            HttpServletRequest httpServletRequest,
            @RequestBody CommentUpdateRequest request
    ) {
        String writerId = TokenResolveUtil.resolveTokenAndGetUserId(httpServletRequest);
        return ResponseDto.toResponseEntity(ResponseMessage.COMMENT_UPDATE_SUCCESS, commentService.updateComment(request, writerId, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "id") Long id,HttpServletRequest httpServletRequest) {
        String writerId = TokenResolveUtil.resolveTokenAndGetUserId(httpServletRequest);
        boolean result = commentService.deleteComment(id, writerId);
        return result ?
                ResponseDto.toResponseEntity(ResponseMessage.COMMENT_DELETE_SUCCESS, true) :
                ResponseDto.toResponseEntity(ResponseMessage.COMMENT_DELETE_FAIL, false);
    }
}
