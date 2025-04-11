package com.example.demo1.post.dto.request;

import com.example.demo1.post.entity.PostStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PostCreateReq {
    //Validation -> 입력값들 검증하는 어노테이션
    @NotNull
    @Schema(description = "작성자 ID")
    private Long userId;

    @NotBlank
    @Schema(description = "새로운 게시글 제목")
    private String title;

    @NotBlank
    @Schema(description = "새로운 게시글 내용")
    private String content;

    @NotNull
    @Schema(description = "공개/비공개")
    private PostStatus status;
}
