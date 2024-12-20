package com.se.error.exception.redis;

import com.se.error.BusinessException;
import com.se.error.dto.ErrorMessage;

public class RedisErrorException extends BusinessException {
    public RedisErrorException() {
        super(ErrorMessage.REDIS_ERROR);
    }
}
