package com.se.board.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.se.board.domain.Board;
import com.se.board.dto.request.BoardCreateRequest;
import com.se.board.dto.request.BoardSearchRequest;
import com.se.board.dto.request.BoardUpdateRequest;
import com.se.student.repository.StudentRepository;
import com.se.util.DateUtil;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.se.board.domain.QBoard.board;

@Repository
public class BoardRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;
    private final StudentRepository studentRepository;

    public BoardRepository(EntityManager em, StudentRepository studentRepository) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Board create(BoardCreateRequest request, String writerId) {
        Board board = Board.builder()
                .title(request.getTitle())
                .content(request.getContext())
                .student(studentRepository.readById(writerId))
                .boardCategory(request.getCategory())
                .createdAt(DateUtil.getLocalDateTime())
                .build();

        em.persist(board);
        return board;
    }

    @Transactional(readOnly = true)
    public Board readById(Long id) {
        return queryFactory
                .selectFrom(board)
                .where(boardIdEq(id))
                .fetchOne();
    }

    private BooleanExpression boardIdEq(Long id) {
        return id != null ? board.id.eq(id) : null;
    }

    @Transactional(readOnly = true)
    public List<Board> readAll() {
        return queryFactory
                .selectFrom(board)
                .fetch();
    }

    @Transactional(readOnly = true)
    public List<Board> readAllByCondition(BoardSearchRequest request) {
        return queryFactory.selectFrom(board)
                .where(titleLike(request.getTitle()))
                .where(writerIdEq(request.getWriterId()))
                .fetch();
    }

    private BooleanExpression titleLike(String title){
        return title != null ? board.title.like("%"+title+"%") : null;
    }

    @Transactional
    public Board update(BoardUpdateRequest request, String writerId){
        queryFactory.update(board)
                .where(boardIdEq(request.getId()), writerIdEq(writerId))
                .set(board.title, request.getTitle())
                .set(board.content, request.getContext())
                .set(board.modifiedAt, DateUtil.getLocalDateTime())
                .execute();

        return queryFactory
                .selectFrom(board)
                .where(boardIdEq(request.getId()))
                .fetchOne();
    }

    private BooleanExpression writerIdEq(String writerId) {
        return writerId != null ? board.student.id.eq(writerId) : null;
    }

    @Transactional
    public long delete(Long id, String writerId) {
        return queryFactory
                .delete(board)
                .where(boardIdEq(id), writerIdEq(writerId))
                .execute();
    }

    @Transactional(readOnly = true)
    public List<Board> readByWriterId(String writerId) {
        return queryFactory.selectFrom(board)
                .where(writerIdEq(writerId))
                .fetch();
    }
}
