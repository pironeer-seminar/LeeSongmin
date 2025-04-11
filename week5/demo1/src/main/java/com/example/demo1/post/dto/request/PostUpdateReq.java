package com.example.demo1.post.dto.request;

import com.example.demo1.post.entity.PostStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class PostUpdateReq {

    @Schema(description = "수정할 게시글 제목")
    private String title;

    @Schema(description = "수정할 게시글 내용")
    private String content;

    @Schema(description = "공개/비공개")
    private PostStatus status;
}
