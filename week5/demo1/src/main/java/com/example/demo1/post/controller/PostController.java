package com.example.demo1.post.controller;

import com.example.demo1.common.dto.ApiRes;
import com.example.demo1.common.type.PostSuccessType;
import com.example.demo1.post.dto.request.PostCreateReq;
import com.example.demo1.post.dto.request.PostUpdateReq;
import com.example.demo1.post.dto.response.PostSearchRes;
import com.example.demo1.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 생성
    @PostMapping("")
//    public Long create(@RequestBody @Valid PostCreateReq req) {
//        return postService.create(req);
//    } // Valid 어노테이션 해야 검증 진행함
    public ApiRes<?> create(@Valid @RequestBody PostCreateReq req) {
        postService.create(req);
        return ApiRes.success(PostSuccessType.CREATE);
    }

    // 목록조회
    @GetMapping("")
    public ApiRes<List<PostSearchRes>> search() {
        return ApiRes.success(PostSuccessType.Get_ALL, postService.search());
    }

    // 단일조회
    @GetMapping("/{postId}")
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
//    public Long update(
//            @PathVariable("postId") Long postId,
//            @RequestBody PostUpdateReq req) {
//        return postService.update(postId, req);
//    }
    public ApiRes<?> update(@PathVariable Long postId, @RequestBody @Valid PostUpdateReq req) {
        postService.update(postId, req);
        return ApiRes.success(PostSuccessType.UPDATE, postService.update(postId, req));
    }

    // 삭제
    @DeleteMapping("/{postId}")
//    public Long delete(
//            @PathVariable("postId") Long postId
//    ) {
//        return postService.delete(postId);
//    }
    public ApiRes<?> delete(@PathVariable Long postId) {
        postService.delete(postId);
        return ApiRes.success(PostSuccessType.DELETE, postService.delete(postId));
    }
}
