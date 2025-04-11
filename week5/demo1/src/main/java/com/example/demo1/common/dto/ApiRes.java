package com.example.demo1.common.dto;

import com.example.demo1.common.exception.BaseException;
import com.example.demo1.common.type.ErrorType;
import com.example.demo1.common.type.SuccessType;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

public record ApiRes<T> (
        Integer status, String code, String message, @JsonInclude(JsonInclude.Include.NON_NULL) T data) {
    // T data는 존재 안할 수도 있음(Body가 없을 수도 있음) -> JsonInclude -> NotNull일 때만 표시해라

    public static ApiRes<?> success(SuccessType successType) {
        return new ApiRes<>(HttpStatus.OK.value(), successType.getCode(), successType.getMessage(), null);
    }

    public static <T> ApiRes<T> success(SuccessType successType, T data) {
        return new ApiRes<>(HttpStatus.OK.value(), successType.getCode(), successType.getMessage(), data);
    }

    public static ApiRes<?> fail(ErrorType errorType, HttpStatus status) {
        return new ApiRes<>(
                status.value(), errorType.getCode(), errorType.getMessage(), null);
    }

    public static <T> ApiRes<T> fail(ErrorType errorType, HttpStatus status, T data) {
        return new ApiRes<>(status.value(), errorType.getCode(), errorType.getMessage(), data);
    }

    public static <T> ApiRes<T> fail(BaseException baseException) {
        ErrorType errorType = baseException.getErrorType();
        return new ApiRes<>(
                baseException.getHttpStatusCode(),
                baseException.getErrorType().getCode(),
                baseException.getErrorType().getMessage(),
                null);
    }

}
