package com.se.board.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardCategory {
    STUDY_RECRUITMENT("스터디 모집"),
    QUESTION("질문"),
    STAFF_NOTICE("개발자 공지"),

    ;


    private final String value;

    public String getValue(String value) {
        return BoardCategory.valueOf(value).getValue();
    }
}
