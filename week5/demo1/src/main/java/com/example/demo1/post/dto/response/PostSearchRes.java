package com.example.demo1.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostSearchRes {

    @Schema(description = "작성자 ID")
    private Long userId;

    @Schema(description = "게시글 ID")
    private Long postId;

    @Schema(description = "게시글 제목")
    private String title;

    @Schema(description = "게시글 내용")
    private String content;

    @Schema(description = "게시글 생성 시각")
    private LocalDateTime createdAt;
}
