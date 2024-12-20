package com.se.error.exception.redis;

import com.se.error.BusinessException;
import com.se.error.dto.ErrorMessage;

public class InvalidJwtException extends BusinessException {
    public InvalidJwtException() {
        super(ErrorMessage.INVALID_JWT);
    }
}
