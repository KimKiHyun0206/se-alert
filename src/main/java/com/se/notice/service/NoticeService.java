package com.se.notice.service;

import com.se.notice.dto.NoticeResponse;
import com.se.notice.dto.request.NoticeRegisterRequest;
import com.se.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeResponse save(NoticeRegisterRequest request, Long senderId){
        return noticeRepository.save(request,senderId).toResponse();
    }
}
