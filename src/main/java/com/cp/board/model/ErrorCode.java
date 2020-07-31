package com.cp.board.model;

import lombok.Getter;

import javax.servlet.http.HttpServletResponse;

@Getter
public enum ErrorCode {
    BAD_REQUEST("ER_400", "잘못된 요청입니다.", HttpServletResponse.SC_BAD_REQUEST),
    INTERNAL_SERVER_ERROR("ER_500", "서버의 오류가 발생했습니다.", HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
    NOT_FOUND_DATA("ER_1001", "데이터가 없습니다.", HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
    ;

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
