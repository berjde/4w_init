package com.cp.board.dto;

import com.cp.board.model.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> implements Serializable {
    private int status = 200;
    private String errorCode;
    private String message;
    private T data;

    @Builder
    public ResponseDTO(T data, ErrorCode errorCode) {
        this.data = data;
        if(errorCode!= null) {
            this.status = errorCode.getStatus();
            this.message = errorCode.getMessage();
            this.errorCode = errorCode.getCode();
        }
    }
}
