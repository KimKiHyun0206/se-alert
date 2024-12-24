package com.se.notice.controller;

import com.se.common.dto.ResponseDto;
import com.se.common.dto.ResponseMessage;
import com.se.notice.dto.NoticeResponse;
import com.se.notice.dto.request.NoticeCreateRequest;
import com.se.notice.dto.request.NoticeUpdateRequest;
import com.se.notice.service.NoticeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping
    public ResponseEntity<?> createNotice(NoticeCreateRequest request, HttpServletRequest httpServletRequest) {
        String id = httpServletRequest.getUserPrincipal().getName();
        NoticeResponse save = noticeService.register(request, id);
        return ResponseDto.toResponseEntity(ResponseMessage.NOTICE_CREATE_SUCCESS, save);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateNotice(
            NoticeUpdateRequest request,
            @PathVariable(value = "id") Long id,
            HttpServletRequest httpServletRequest
    ) {
        String writerId = httpServletRequest.getUserPrincipal().getName();
        NoticeResponse update = noticeService.update(request, id, writerId);
        return update != null ?
                ResponseDto.toResponseEntity(ResponseMessage.NOTICE_UPDATE_SUCCESS, update) :
                ResponseDto.toResponseEntity(ResponseMessage.NOTICE_UPDATE_FAILED, null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable(value = "id") Long id) {
        NoticeResponse notice = noticeService.read(id);
        return ResponseDto.toResponseEntity(ResponseMessage.NOTICE_FIND_SUCCESS, notice);
    }

    @GetMapping
    public ResponseEntity<?> readAll(@RequestParam(value = "page", defaultValue = "10") Long page) {
        List<NoticeResponse> all = noticeService.readAll(page);
        return ResponseDto.toResponseEntity(ResponseMessage.NOTICE_FIND_SUCCESS, all);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long noticeId, HttpServletRequest httpServletRequest) {
        String id = httpServletRequest.getUserPrincipal().getName();
        noticeService.delete(noticeId, id);
        return ResponseDto.toResponseEntity(ResponseMessage.NOTICE_DELETE_SUCCESS, null);
    }
}
