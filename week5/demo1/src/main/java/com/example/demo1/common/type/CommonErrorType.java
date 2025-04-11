package com.example.demo1.common.type;

public enum CommonErrorType implements ErrorType {
    INTERNAL_SERVER("COMMON_1", "알 수 없는 오류가 발생했습니다"),
    INVALID_INPUT("COMMON_2", "입력값이 유효하지 않습니다."),
    INTERNAL_SERVER_ERROR("COMMON_3", "서버 내부 오류가 발생했습니다.");

    private final String code;
    private final String message;

    CommonErrorType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
