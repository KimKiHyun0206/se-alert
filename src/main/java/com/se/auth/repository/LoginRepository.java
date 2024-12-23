package com.se.auth.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.se.error.exception.student.InvalidIdException;
import com.se.student.domain.Student;
import jakarta.persistence.EntityManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.se.student.domain.QPermission.permission;
import static com.se.student.domain.QStudent.student;

@Repository
public class LoginRepository implements UserDetailsService {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public LoginRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student loaded = queryFactory
                .selectFrom(student)
                .where(studentIdEq(Long.valueOf(username)))
                .fetchOne();

        if (loaded == null) throw new InvalidIdException(username);

        return createUser(loaded);
    }

    private BooleanExpression studentIdEq(Long id) {
        return id != null ? student.id.eq(String.valueOf(id)) : null;
    }

    private User createUser(Student student) {
        List<SimpleGrantedAuthority> list = queryFactory
                .selectFrom(permission)
                .where(permission.id.eq(student.getPermission()))
                .fetchOne()
                .getPermissions()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        return new User(String.valueOf(student.getId()), student.getPassword(), list);
    }
}
