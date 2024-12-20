package com.se.error.exception.student;

import com.se.error.BusinessException;
import com.se.error.dto.ErrorMessage;

public class InvalidPhoneNumberRegex extends BusinessException {
    public InvalidPhoneNumberRegex(String message) {
        super(ErrorMessage.INVALID_PHONE_NUMBER_REGEX, message);
    }
}
