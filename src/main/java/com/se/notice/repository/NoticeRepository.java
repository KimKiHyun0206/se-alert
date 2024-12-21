package com.se.notice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.se.notice.domain.Notice;
import com.se.notice.dto.NoticeResponse;
import com.se.notice.dto.request.NoticeRegisterRequest;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class NoticeRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public NoticeRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Transactional
    public Notice save(NoticeRegisterRequest request, Long senderId) {
        Notice notice = Notice.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .receiverPermission(request.getReceiverPermission())
                .senderId(senderId)
                .build();

        em.persist(notice);
        return notice;
    }
}
