package com.example.demo1.post.controller;

import com.example.demo1.common.dto.ApiRes;
import com.example.demo1.common.type.PostSuccessType;
import com.example.demo1.post.dto.request.PostCreateReq;
import com.example.demo1.post.dto.request.PostUpdateReq;
import com.example.demo1.post.dto.response.PostSearchRes;
import com.example.demo1.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Post")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 생성
    @PostMapping("")
    @Operation(summary = "게시글 생성", description = "게시글을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 생성에 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.")
    })
//    public Long create(@RequestBody @Valid PostCreateReq req) {
//        return postService.create(req);
//    } // Valid 어노테이션 해야 검증 진행함
    public ApiRes<?> create(@Valid @RequestBody PostCreateReq req) {
        postService.create(req);
        return ApiRes.success(PostSuccessType.CREATE);
    }

    // 목록조회
    @GetMapping("")
    @Operation(summary = "게시글 목록 조회", description = "게시글 전체 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 전체 목록 조회에 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.")
    })
    public ApiRes<List<PostSearchRes>> search() {
        return ApiRes.success(PostSuccessType.Get_ALL, postService.search());
    }

    // 단일조회
    @GetMapping("/{postId}")
    @Operation(summary = "게시글 상세 목록 조회", description = "게시글 상세 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 상세 목록 조회에 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.")
    })
//    public PostSearchRes detail(
//            @PathVariable("postId") Long postId
//    ) {
//        return postService.detail(postId);
//    }
    public ApiRes<PostSearchRes> detail(@PathVariable Long postId) {
        return ApiRes.success(PostSuccessType.Get_DETAIL, postService.detail(postId));
    }

    // 수정
    @PutMapping("/{postId}")
    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 수정에 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.")
    })
//    public Long update(
//            @PathVariable("postId") Long postId,
//            @RequestBody PostUpdateReq req) {
//        return postService.update(postId, req);
//    }
    public ApiRes<?> update(
            @Parameter(description = "게시글 ID")
            @PathVariable Long postId,
            @RequestBody @Valid PostUpdateReq req) {
        postService.update(postId, req);
        return ApiRes.success(PostSuccessType.UPDATE, postService.update(postId, req));
    }

    // 삭제
    @DeleteMapping("/{postId}")
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 삭제에 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.")
    })
//    public Long delete(
//            @PathVariable("postId") Long postId
//    ) {
//        return postService.delete(postId);
//    }
    public ApiRes<?> delete(
            @Parameter(description = "게시글 ID")
            @PathVariable Long postId) {
        postService.delete(postId);
        return ApiRes.success(PostSuccessType.DELETE, postService.delete(postId));
    }
}
