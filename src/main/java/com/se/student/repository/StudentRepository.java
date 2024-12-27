package com.se.student.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.se.student.domain.Student;
import com.se.student.domain.vo.Name;
import com.se.student.domain.vo.PhoneNumber;
import com.se.student.dto.request.StudentCreateRequest;
import com.se.student.dto.request.StudentUpdateRequest;
import com.se.student.dto.response.StudentResponse;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.se.student.domain.QStudent.student;

@Repository
public class StudentRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public StudentRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Transactional
    public Student create(StudentCreateRequest request) {
        Student save = Student.builder()
                .id(request.getId())
                .name(new Name(request.getName()))
                .password("123456789a")
                .phoneNumber(new PhoneNumber("010-1234-5678"))
                .aboutMe("신입생")
                .permission(1L)
                .build();

        em.persist(save);

        return save;
    }

    /**
     * @param id 조회할 학번
     */
    @Transactional(readOnly = true)
    public Student readById(String id) {
        return queryFactory
                .selectFrom(student)
                .where(studentIdEq(id))
                .fetchOne();
    }

    private BooleanExpression studentIdEq(String id) {
        return id != null ? student.id.eq(id) : null;
    }


    @Transactional(readOnly = true)
    public List<Student> readAll() {
        return queryFactory
                .selectFrom(student)
                .fetch();
    }

    /**
     * @param year 조회하는데 필요한 학번
     * @implSpec like 문을 사용
     */
    @Transactional(readOnly = true)
    public List<Student> readByYear(Long year) {
        return queryFactory
                .selectFrom(student)
                .where(student.id.like("__" + year + "%"))
                .fetch();
    }

    /**
     * @param name 조회하는데 필요한 이름
     */
    @Transactional(readOnly = true)
    public List<Student> readByName(String name) {
        return queryFactory
                .selectFrom(student)
                .where(studentNameEq(name))
                .fetch();
    }

    private BooleanExpression studentNameEq(String name) {
        return name != null ? student.name.eq(new Name(name)) : null;
    }

    public List<Student> readByPermission(Long permission) {
        return queryFactory
                .selectFrom(student)
                .where(studentPermissionEq(permission))
                .fetch();
    }

    private BooleanExpression studentPermissionEq(Long permission) {
        return permission != null ? student.permission.eq(permission) : null;
    }

    /**
     * @implNote JPAUpdateClause에 자체적으로 null인지 검사해주는 기능이 있는데 String이 만약 "" 값이라면 이를 걸러내지 못하는 문제점이 있다
     * */
    @Transactional
    public Student update(String id, StudentUpdateRequest request) {
        JPAUpdateClause clause = queryFactory
                .update(student).where(studentIdEq(id))
                .set(student.id, request.getId())
                .set(student.name, new Name(request.getName()))
                .set(student.password, request.getPassword())
                .set(student.phoneNumber, new PhoneNumber(request.getPhoneNumber()))
                .set(student.aboutMe, request.getAboutMe());

        finishUpdate();

        return readById(request.getId());
    }

    private void finishUpdate() {
        em.clear();
        em.flush();
    }
}
