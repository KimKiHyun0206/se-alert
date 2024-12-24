package com.se.notice.dto.request;

import lombok.Data;

@Data
public class NoticeCreateRequest {
    private String title;
    private String content;
    private Long receiverPermission;
}
