package com.se.comment.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.se.board.domain.Board;
import com.se.board.repository.BoardRepository;
import com.se.comment.domain.Comment;
import com.se.comment.domain.QComment;
import com.se.comment.dto.request.CommentCreateRequest;
import com.se.comment.dto.request.CommentUpdateRequest;
import com.se.student.domain.Student;
import com.se.student.repository.StudentRepository;
import com.se.util.DateUtil;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import static com.se.board.domain.QBoard.board;
import static com.se.comment.domain.QComment.comment;
import static com.se.student.domain.QStudent.student;

@Repository
public class CommentRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;
    private final BoardRepository boardRepository;
    private final StudentRepository studentRepository;

    public CommentRepository(EntityManager em, BoardRepository boardRepository, StudentRepository studentRepository) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
        this.boardRepository = boardRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Comment createComment(CommentCreateRequest request, String writerId) {
        Board resultBoard = boardRepository.readById(request.getBoardId());
        Student resultStudent = studentRepository.readById(writerId);
        Comment writedComment = Comment.builder()
                .context(request.getComment())
                .student(resultStudent)
                .board(resultBoard)
                .createdAt(DateUtil.getLocalDateTime())
                .build();


        resultBoard.addComment(writedComment);
        em.persist(writedComment);


        return writedComment;
    }

    @Transactional(readOnly = true)
    public Comment readComment(Long commentId) {
        return queryFactory
                .selectFrom(comment)
                .join(comment.student, student).fetchJoin()
                .join(comment.board, board).fetchJoin()
                .where(commentIdEq(commentId))
                .distinct()
                .fetchOne();
    }

    @Transactional
    public Comment updateComment(CommentUpdateRequest request, String writerId, Long commentId) {
        queryFactory.update(comment)
                .where(commentIdEq(commentId))
                .where(studentIdEq(writerId))
                .set(comment.context, request.getContext())
                .set(comment.modifiedAt, DateUtil.getLocalDateTime())
                .execute();

        return readComment(commentId);
    }

    @Transactional
    public boolean deleteComment(Long commentId, String writerId) {
        long execute = queryFactory.delete(comment)
                .where(commentIdEq(commentId))
                .where(studentIdEq(writerId))
                .execute();
        return execute > 0;
    }

    private BooleanExpression commentIdEq(Long commentId){
        return commentId != null ? comment.id.eq(commentId) : null;
    }

    private BooleanExpression studentIdEq(String studentId){
        return studentId != null ? comment.student.id.eq(studentId) : null;
    }
}
