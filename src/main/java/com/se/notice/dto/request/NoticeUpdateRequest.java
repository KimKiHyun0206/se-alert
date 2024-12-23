package com.se.notice.dto.request;

import lombok.Data;

@Data
public class NoticeUpdateRequest {
    private String title;
    private String content;
    private Long receiverPermission;
}
