package com.se.error.exception.board;

import com.se.error.BusinessException;
import com.se.error.dto.ErrorMessage;

public class BoardUpdateFailException extends BusinessException {
    public BoardUpdateFailException() {
        super(ErrorMessage.BOARD_UPDATE_FAIL);
    }
}
