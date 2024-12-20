package com.se.error.exception.student;

import com.se.error.BusinessException;
import com.se.error.dto.ErrorMessage;

public class InvalidNameException extends BusinessException {
    public InvalidNameException(String message) {
        super(ErrorMessage.INVALID_NAME_REGEX, message);
    }
}
