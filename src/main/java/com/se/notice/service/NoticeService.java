package com.se.notice.service;

import com.se.notice.dto.NoticeResponse;
import com.se.notice.dto.request.NoticeCreateRequest;
import com.se.notice.dto.request.NoticeUpdateRequest;
import com.se.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeResponse register(NoticeCreateRequest request, String id) {
        return noticeRepository.save(request, id);
    }

    /**
     * @implNote 만약 작성자와 요청자의 id가 값지 않다면 null값을 반환함
     * */
    public NoticeResponse update(NoticeUpdateRequest request, Long id, String writerId) {
        NoticeResponse notice = noticeRepository.findById(id);

        if (notice != null && notice.getSenderId().equals(writerId)) {
            return noticeRepository.update(request, id);
        }
        return null;
    }

    public NoticeResponse get(Long id) {
        return noticeRepository.findById(id);
    }

    public List<NoticeResponse> getAll(@RequestParam(value = "page", defaultValue = "10") Long page) {
        return noticeRepository.findAll(page);
    }

    /**
     * @implNote 공지의 id와 요청자의 id가 같으면 삭제 가능
     */
    public void delete(Long noticeId, String writerId) {
        NoticeResponse notice = noticeRepository.findById(noticeId);
        if (notice != null && notice.getSenderId().equals(writerId)) {
            noticeRepository.delete(noticeId);
        }
    }
}
