package com.cp.board.advice;

import com.cp.board.dto.ResponseDTO;
import com.cp.board.exception.ApiException;
import com.cp.board.model.ErrorCode;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionAdvice {

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseDTO ApiServerError(ApiException e) {
        log.error("{}: {}", HttpStatus.INTERNAL_SERVER_ERROR, e);
        return ResponseDTO.builder()
                .errorCode(e.getErrorCode())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseDTO handleInternalServerError(Exception e) {
        log.error("{}: {}", HttpStatus.INTERNAL_SERVER_ERROR, e);
        return ResponseDTO.builder()
                .errorCode(ErrorCode.INTERNAL_SERVER_ERROR)
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("{}: {}", HttpStatus.BAD_REQUEST, e);

        return ResponseDTO.builder()
                .errorCode(ErrorCode.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseDTO handleBindException(BindException e) {
        List<Map> result = e.getBindingResult().getFieldErrors().stream()
                .map(f -> ImmutableMap.builder()
                        .put("fieldName", f.getField())
                        .put("message", f.getDefaultMessage())
                        .build()
                )
                .collect(Collectors.toList());

        log.error("{}: {}", HttpStatus.BAD_REQUEST, e);
        return ResponseDTO.builder()
                .errorCode(ErrorCode.BAD_REQUEST)
                .data(result)
                .build();
    }

}