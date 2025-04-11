package com.example.demo1.common.type;

public enum PostSuccessType implements SuccessType {
    Get_ALL("Post_1", "게시글 목록 조회에 성공하였습니다.");
    private final String code;
    private final String message;

    PostSuccessType(String code, String message) {
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
