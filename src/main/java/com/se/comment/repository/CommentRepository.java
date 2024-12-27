package com.se.comment.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.se.board.domain.Board;
import com.se.board.repository.BoardRepository;
import com.se.comment.domain.Comment;
import com.se.comment.dto.request.CommentCreateRequest;
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

    private BooleanExpression boardIdEq(Long boardId) {
        return boardId != null ? board.id.eq(boardId) : null;
    }

    private BooleanExpression studentIdEq(String studentId) {
        return studentId != null ? student.id.eq(studentId) : null;
    }

    public Comment readComment(Long commentId) {
        return queryFactory.selectFrom(comment)
                .where(comment.id.eq(commentId))
                .fetchOne();
    }
}
