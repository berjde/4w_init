package com.cp.board.exception;

import com.cp.board.model.ErrorCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private ErrorCode errorCode;

    public ApiException(){
        super(ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
        this.errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
    }
    public ApiException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
