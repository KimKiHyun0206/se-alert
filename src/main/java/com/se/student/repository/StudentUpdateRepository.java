package com.se.student.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.se.error.exception.student.InvalidIdException;
import com.se.student.domain.Student;
import com.se.student.domain.vo.Name;
import com.se.student.domain.vo.PhoneNumber;
import com.se.student.dto.request.StudentUpdateRequest;
import com.se.student.dto.response.StudentResponse;
import jakarta.persistence.EntityManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.se.student.domain.QStudent.student;

@Repository
public class StudentUpdateRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;
    private final PasswordEncoder passwordEncoder;

    public StudentUpdateRepository(EntityManager em, PasswordEncoder passwordEncoder) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public StudentResponse update(Long id, StudentUpdateRequest request) {
        JPAUpdateClause clause = queryFactory
                .update(student)
                .set(student.id, request.getId())
                .set(student.name, new Name(request.getName()))
                .set(student.password, request.getPassword())
                .set(student.phoneNumber, new PhoneNumber(request.getPhoneNumber()))
                .set(student.aboutMe, request.getAboutMe());

        if (request.getId() != null) clause.set(student.id, request.getId());
        if (request.getPassword() != null) clause.set(student.password, request.getPassword());
        if (request.getName() != null) clause.set(student.name, new Name(request.getName()));
        if (request.getPhoneNumber() != null) clause.set(student.phoneNumber, new PhoneNumber(request.getPhoneNumber()));
        if (request.getAboutMe() != null) clause.set(student.aboutMe, request.getAboutMe());

        finishUpdate();

        return queryFactory
                .selectFrom(student)
                .where(studentIdEq(request.getId()))
                .fetchOne()
                .toResponse();
    }

    private BooleanExpression studentIdEq(Long id) {
        return id != null ? student.id.eq(id) : null;
    }

    private void finishUpdate() {
        em.clear();
        em.flush();
    }
}
