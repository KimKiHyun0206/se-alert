package com.se.error.exception.student;

import com.se.error.BusinessException;
import com.se.error.dto.ErrorMessage;

public class InvalidIdException extends BusinessException {
    public InvalidIdException(String id) {
        super(ErrorMessage.NOT_FOUND_USERS_EXCEPTION, "요청한 id : " + id);
    }
}
