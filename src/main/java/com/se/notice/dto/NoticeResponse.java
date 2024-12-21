package com.se.notice.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class NoticeResponse {
    private Long id;
    private String title;
    private String content;
    private Long senderId;
    private Long receiverPermission;

    @Builder
    public NoticeResponse(Long id, String title, String content, Long senderId, Long receiverPermission) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.senderId = senderId;
        this.receiverPermission = receiverPermission;
    }
}