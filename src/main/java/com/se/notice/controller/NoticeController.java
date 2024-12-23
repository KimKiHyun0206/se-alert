package com.se.notice.controller;

import com.se.common.dto.ResponseDto;
import com.se.common.dto.ResponseMessage;
import com.se.notice.dto.NoticeResponse;
import com.se.notice.dto.request.NoticeRegisterRequest;
import com.se.notice.dto.request.NoticeUpdateRequest;
import com.se.notice.repository.NoticeRepository;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notice")
public class NoticeController {

    private final NoticeRepository noticeRepository;

    @PostMapping
    public ResponseEntity<?> postNotice(NoticeRegisterRequest request, HttpServletRequest httpServletRequest) {
        String name = httpServletRequest.getUserPrincipal().getName();
        NoticeResponse save = noticeRepository.save(request, name);
        return ResponseDto.toResponseEntity(ResponseMessage.NOTICE_CREATE_SUCCESS, save);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateNote(NoticeUpdateRequest request, @PathVariable(value = "id") Long id) {
        NoticeResponse update = noticeRepository.update(request, id);
        return ResponseDto.toResponseEntity(ResponseMessage.NOTICE_UPDATE_SUCCESS, update);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") Long id) {
        NoticeResponse notice = noticeRepository.findById(id);
        return ResponseDto.toResponseEntity(ResponseMessage.NOTICE_FIND_SUCCESS, notice);
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "10") Long page) {
        List<NoticeResponse> all = noticeRepository.findAll(page);
        return ResponseDto.toResponseEntity(ResponseMessage.NOTICE_FIND_SUCCESS, all);
    }
}
