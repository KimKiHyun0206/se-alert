package com.se.notice.domain;

import com.se.common.BaseEntity;
import com.se.notice.dto.NoticeResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String senderId;
    private Long receiverPermission;

    @Builder
    public Notice(String title, String content, String senderId, Long receiverPermission) {
        this.title = title;
        this.content = content;
        this.senderId = senderId;
        this.receiverPermission = receiverPermission;
    }

    public NoticeResponse toResponse() {
        return NoticeResponse.builder()
                .id(id)
                .title(title)
                .content(content)
                .senderId(senderId)
                .receiverPermission(receiverPermission)
                .build();
    }
}
