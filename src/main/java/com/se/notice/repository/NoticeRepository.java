package com.se.notice.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.se.notice.domain.Notice;
import com.se.notice.dto.NoticeResponse;
import com.se.notice.dto.request.NoticeRegisterRequest;
import com.se.notice.dto.request.NoticeUpdateRequest;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.se.notice.domain.QNotice.notice;

@Repository
public class NoticeRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public NoticeRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Transactional
    public NoticeResponse save(NoticeRegisterRequest request, String senderId) {
        Notice notice = Notice.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .receiverPermission(request.getReceiverPermission())
                .senderId(senderId)
                .build();

        em.persist(notice);
        return notice.toResponse();
    }


    @Transactional(readOnly = true)
    public NoticeResponse findById(Long id) {
        return queryFactory
                .selectFrom(notice)
                .where(noticeIdEq(id))
                .fetchOne()
                .toResponse();
    }

    @Transactional(readOnly = true)
    public List<NoticeResponse> findAll(Long num) {
        return queryFactory
                .selectFrom(notice)
                .limit(num)
                .fetch()
                .stream()
                .map(Notice::toResponse)
                .toList();
    }


    @Transactional
    public NoticeResponse update(NoticeUpdateRequest request, Long noticeId) {
        queryFactory
                .update(notice)
                .set(notice.title, request.getTitle())
                .set(notice.receiverPermission, request.getReceiverPermission())
                .set(notice.content, request.getContent())
                .execute();

        finishUpdate();

        return queryFactory
                .selectFrom(notice)
                .where(noticeIdEq(noticeId))
                .fetchOne()
                .toResponse();
    }

    private BooleanExpression noticeIdEq(Long noticeId) {
        return noticeId != null ? notice.id.eq(noticeId) : null;
    }

    private void finishUpdate() {
        em.clear();
        em.flush();
    }
}
