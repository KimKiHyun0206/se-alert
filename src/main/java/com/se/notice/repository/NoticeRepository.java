package com.se.notice.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.se.notice.domain.Notice;
import com.se.notice.dto.NoticeResponse;
import com.se.notice.dto.request.NoticeCreateRequest;
import com.se.notice.dto.request.NoticeUpdateRequest;
import com.se.util.DateUtil;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.se.notice.domain.QNotice.notice;

@Slf4j
@Repository
public class NoticeRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public NoticeRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Transactional
    public NoticeResponse create(NoticeCreateRequest request, String senderId) {
        Notice notice = Notice.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .receiverPermission(request.getReceiverPermission())
                .senderId(senderId)
                .createdAt(DateUtil.getLocalDateTime())
                .build();

        em.persist(notice);
        return notice.toResponse();
    }


    @Transactional(readOnly = true)
    public NoticeResponse readById(Long id) {
        return queryFactory
                .selectFrom(notice)
                .where(noticeIdEq(id))
                .fetchOne()
                .toResponse();
    }

    @Transactional(readOnly = true)
    public List<NoticeResponse> readAll(Long num) {
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
                .where(noticeIdEq(noticeId))
                .set(notice.title, request.getTitle())
                .set(notice.receiverPermission, request.getReceiverPermission())
                .set(notice.content, request.getContent())
                .set(notice.modifiedAt, DateUtil.getLocalDateTime())
                .execute();

        finishUpdate();

        return readById(noticeId);
    }

    private BooleanExpression noticeIdEq(Long noticeId) {
        return noticeId != null ? notice.id.eq(noticeId) : null;
    }

    private BooleanExpression writerIdEq(String writerId) {
        return writerId != null ? notice.senderId.eq(writerId) : null;
    }

    private void finishUpdate() {
        em.clear();
        em.flush();
    }

    public void delete(Long id) {
        long execute = queryFactory.delete(notice).where(noticeIdEq(id)).execute();
        log.info("[ SYSTEM ] NOTICE DELETE {}", execute);
    }
}
