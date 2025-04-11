package com.example.demo1.common.type;

public enum PostSuccessType implements SuccessType {
    Get_ALL("Post_1", "게시글 목록 조회에 성공하였습니다."),
    Get_DETAIL("Post_2", "게시글 상세 목록 조회에 성공하였습니다." ),
    UPDATE("Post_3", "게시글 수정에 성공하였습니다." ),
    DELETE("Post_4", "게시글 삭제에 성공하였습니다." ),
    CREATE("Post_5", "게시글 생성에 성공하였습니다." );



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
