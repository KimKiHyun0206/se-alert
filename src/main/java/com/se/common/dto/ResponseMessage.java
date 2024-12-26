package com.se.common.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseMessage {

    SUCCESS(HttpStatus.OK,"SUCCESS"),


    //User
    SUCCESS_SIGN_UP_EMAIL_CHECK(HttpStatus.OK, "중복되지 않은 이메일"),
    CREATE_SUCCESS_STUDENT(HttpStatus.CREATED, "회원 회원 가입 성공"),
    SUCCESS_LOAD_STUDENT_INFORMATION(HttpStatus.OK, "회원 정보 조회 성공"),
    SUCCESS_SEARCH_ALL_STUDENT(HttpStatus.OK, "모든 회원 조회 성공"),
    SUCCESS_UPDATE_STUDENT(HttpStatus.OK, "회원 정보 변경 성공"),
    SUCCESS_SEARCH_STUDENT_NUMBER(HttpStatus.OK,"학번을 통한 회원 정보 조회 성공"),
    SUCCESS_SEARCH_STUDENT_NAME(HttpStatus.OK,"이름을 통한 회원 정보 조회 성공"),
    SUCCESS_SEARCH_STUDENT_PERMISSION(HttpStatus.OK,"권한 레벨에 따른 회원 정보 조회 성공"),
    SUCCESS_STUDENT_INFO_FETCH(HttpStatus.OK, "토큰을 통한 회원 정보 가져오기 성공"),


    //BOARD
    BOARD_CREATE_SUCCESS(HttpStatus.CREATED,"Board를 생성 했습니다."),
    BOARD_UPDATE_SUCCESS(HttpStatus.OK,"Board를 수정 하였습니다."),
    BOARD_READ_SUCCESS(HttpStatus.OK,"해당 Board 조회에 성공했습니다."),
    BOARD_READ_ALL_SUCCESS(HttpStatus.OK,"전체 Board 조회를 성공 했습니다."),
    //READ_SUCCESS_BOARD_CATEGORY(HttpStatus.OK,"Board Category 조회를 성공 했습니다."),

    READ_SUCCESS_BOARD_VIEW_COUNT(HttpStatus.OK,"해당 Board 조회수 조회를 성공 했습니다."),
    BOARD_DELETE_SUCCESS(HttpStatus.OK,"해당 Board 삭제 성공 했습니다."),
    BOARD_DELETE_FAIL(HttpStatus.BAD_REQUEST,"Board 삭제에 실패했습니다"),
    //UPLOAD_IMAGE_SUCCESS(HttpStatus.CREATED, "이미지 업로드 성공"),


    //NOTICE
    NOTICE_CREATE_SUCCESS(HttpStatus.OK,"공지 생성 성공했습니다"),
    NOTICE_UPDATE_SUCCESS(HttpStatus.OK, "공지 수정 성공했습니다"),
    NOTICE_FIND_SUCCESS(HttpStatus.OK,"공지 조회 성공했습니다"),
    NOTICE_DELETE_SUCCESS(HttpStatus.NO_CONTENT,"공지 삭제 성공했습니다"),
    NOTICE_UPDATE_FAILED(HttpStatus.BAD_REQUEST, "공지 수정에 실패했습니다"),

    ;

    public final static String SUCCESS_MESSAGE = "SUCCESS";
    private final static String NOT_FOUND_MESSAGE = "NOT FOUND";


    private final HttpStatus status;
    private final String message;

    ResponseMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}