package com.se.student.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.se.student.domain.Student;
import com.se.student.domain.vo.Name;
import com.se.student.domain.vo.PhoneNumber;
import com.se.student.dto.request.StudentRegisterRequest;
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
    public StudentResponse save(StudentRegisterRequest request) {
        Student save = Student.builder()
                .id(request.getId())
                .name(new Name(request.getName()))
                .password("123456789a")
                .phoneNumber(new PhoneNumber("010-1234-5678"))
                .aboutMe("신입생")
                .permission(1L)
                .build();

        em.persist(save);

        return save.toResponse();
    }

    /**
     * @param id 조회할 학번
     */
    @Transactional(readOnly = true)
    public StudentResponse findById(Long id) {
        return queryFactory
                .selectFrom(student)
                .where(studentIdEq(id))
                .fetchOne()
                .toResponse();
    }

    private BooleanExpression studentIdEq(Long id) {
        return id != null ? student.id.eq(id) : null;
    }


    @Transactional(readOnly = true)
    public List<StudentResponse> findAll() {
        return queryFactory
                .selectFrom(student)
                .stream()
                .map(Student::toResponse)
                .toList();
    }

    /**
     * @param year 조회하는데 필요한 학번
     * @implSpec like 문을 사용
     */
    @Transactional(readOnly = true)
    public List<StudentResponse> findByYear(Long year) {
        return queryFactory
                .selectFrom(student)
                .where(student.id.like("__" + year + "%"))
                .fetch()
                .stream()
                .map(Student::toResponse)
                .toList();
    }

    /**
     * @param name 조회하는데 필요한 이름
     */
    @Transactional(readOnly = true)
    public List<StudentResponse> findByName(String name) {
        return queryFactory
                .selectFrom(student)
                .where(studentNameEq(name))
                .fetch()
                .stream()
                .map(Student::toResponse)
                .toList();
    }

    private BooleanExpression studentNameEq(String name) {
        return name != null ? student.name.eq(new Name(name)) : null;
    }

    public List<StudentResponse> findByPermission(Long permission) {
        return queryFactory
                .selectFrom(student)
                .where(studentPermissionEq(permission))
                .fetch()
                .stream()
                .map(Student::toResponse)
                .toList();
    }

    private BooleanExpression studentPermissionEq(Long permission) {
        return permission != null ? student.permission.eq(permission) : null;
    }
}
