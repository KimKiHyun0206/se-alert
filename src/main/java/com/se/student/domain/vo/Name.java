package com.se.student.domain.vo;

import com.se.error.exception.student.InvalidNameException;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Getter
@Embeddable
@NoArgsConstructor
public class Name {
    private static final String NAME_REGEX = "^[ㄱ-ㅎ가-힣]{1,5}$";
    private String name;

    public Name(String name) {
        validateName(name);
    }

    private void validateName(String name) {
        if(!Pattern.matches(NAME_REGEX, name)){
            throw new InvalidNameException(name);
        }
        this.name = name;
    }
}