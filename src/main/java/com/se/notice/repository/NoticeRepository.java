package com.se.notice.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.se.notice.domain.Notice;
import com.se.notice.dto.NoticeResponse;
import com.se.notice.dto.request.NoticeCreateRequest;
import com.se.notice.dto.request.NoticeUpdateRequest;
import com.se.student.domain.Student;
import com.se.student.repository.StudentRepository;
import com.se.util.DateUtil;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.se.notice.domain.QNotice.notice;
import static com.se.student.domain.QStudent.student;

@Slf4j
@Repository
public class NoticeRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;
    private final StudentRepository studentRepository;

    public NoticeRepository(EntityManager em, StudentRepository studentRepository) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
        this.studentRepository = studentRepository;
    }


    @Transactional
    public Notice create(NoticeCreateRequest request, String senderId) {
        Student student = studentRepository.readById(senderId);

        Notice notice = Notice.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .receiverPermission(request.getReceiverPermission())
                .student(student)
                .createdAt(DateUtil.getLocalDateTime())
                .build();

        student.addNotice(notice);

        em.persist(notice);
        return notice;
    }


    @Transactional(readOnly = true)
    public Notice readById(Long id) {
        return queryFactory
                .selectFrom(notice)
                .where(noticeIdEq(id))
                .join(notice.student, student).fetchJoin()
                .distinct()
                .fetchOne();
    }

    @Transactional(readOnly = true)
    public List<Notice> readAll(Long num) {
        return queryFactory
                .selectFrom(notice)
                .join(notice.student, student).fetchJoin()
                .distinct()
                .limit(num)
                .fetch();
    }


    @Transactional
    public Notice update(NoticeUpdateRequest request, Long noticeId) {
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
        return writerId != null ? notice.student.id.eq(writerId) : null;
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
