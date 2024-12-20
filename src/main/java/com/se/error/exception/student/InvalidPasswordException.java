package com.se.error.exception.student;

import com.se.error.BusinessException;
import com.se.error.dto.ErrorMessage;

public class InvalidPasswordException extends BusinessException {
    public InvalidPasswordException() {
        super(ErrorMessage.INVALID_PASSWORD_REGEX);
    }
}
