package com.se.board.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.se.board.domain.Board;
import com.se.board.dto.request.BoardCreateRequest;
import com.se.board.dto.response.BoardResponse;
import com.se.board.dto.request.BoardSearchRequest;
import com.se.board.dto.request.BoardUpdateRequest;
import com.se.board.dto.response.BoardWithStudentResponse;
import com.se.student.domain.Student;
import com.se.util.DateUtil;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.se.board.domain.QBoard.board;
import static com.se.student.domain.QStudent.student;

@Repository
public class BoardRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public BoardRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Transactional
    public BoardResponse create(BoardCreateRequest request, String writerId) {
        Board board = Board.builder()
                .title(request.getTitle())
                .content(request.getContext())
                .writerId(writerId)
                .boardCategory(request.getCategory())
                .createdAt(DateUtil.getLocalDateTime())
                .build();

        em.persist(board);
        return board.toResponse();
    }

    @Transactional(readOnly = true)
    public BoardResponse readById(Long id) {
        return queryFactory
                .selectFrom(board)
                .where(boardIdEq(id))
                .fetchOne()
                .toResponse();
    }

    private BooleanExpression boardIdEq(Long id) {
        return id != null ? board.id.eq(id) : null;
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> readAll() {
        return queryFactory
                .selectFrom(board)
                .fetch()
                .stream()
                .map(Board::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> readAllByCondition(BoardSearchRequest request) {
        return queryFactory.selectFrom(board)
                .where(titleLike(request.getTitle()))
                .where(writerIdEq(request.getWriterId()))
                .fetch()
                .stream()
                .map(Board::toResponse)
                .toList();
    }

    private BooleanExpression titleLike(String title){
        return title != null ? board.title.like("%"+title+"%") : null;
    }

    @Transactional
    public BoardResponse update(BoardUpdateRequest request, String writerId){
        queryFactory.update(board)
                .where(boardIdEq(request.getId()), writerIdEq(writerId))
                .set(board.title, request.getTitle())
                .set(board.content, request.getContext())
                .set(board.modifiedAt, DateUtil.getLocalDateTime())
                .execute();

        return queryFactory
                .selectFrom(board)
                .where(boardIdEq(request.getId()))
                .fetchOne()
                .toResponse();
    }

    private BooleanExpression writerIdEq(String writerId) {
        return writerId != null ? board.writerId.eq(writerId) : null;
    }

    @Transactional
    public long delete(Long id, String writerId) {
        return queryFactory
                .delete(board)
                .where(boardIdEq(id), writerIdEq(writerId))
                .execute();
    }

    @Transactional(readOnly = true)
    protected Student readNameByWriterId(String writerId) {
        return queryFactory
                .selectFrom(student)
                .where(student.id.eq(writerId))
                .fetchOne();
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> readByWriterId(String writerId) {
        return queryFactory.selectFrom(board)
                .where(writerIdEq(writerId))
                .fetch()
                .stream()
                .map(Board::toResponse)
                .toList();
    }
}
