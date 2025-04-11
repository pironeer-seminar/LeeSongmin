package com.example.demo1.post.dto.request;

import com.example.demo1.post.entity.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PostCreateReq {
    //Validation -> 입력값들 검증하는 어노테이션
    @NotNull
    private Long userId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private PostStatus status;
}
