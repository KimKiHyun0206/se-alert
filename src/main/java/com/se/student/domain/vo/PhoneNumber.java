package com.se.student.domain.vo;

import com.se.error.exception.student.InvalidPhoneNumberRegex;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Getter
@Embeddable
@NoArgsConstructor
public class PhoneNumber {
    private String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        validatePhoneNumber(phoneNumber);
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (!Pattern.matches("^\\d{3}-\\d{3,4}-\\d{4}$", phoneNumber)) {
            throw new InvalidPhoneNumberRegex(phoneNumber);
        }
        this.phoneNumber = phoneNumber;
    }
}
