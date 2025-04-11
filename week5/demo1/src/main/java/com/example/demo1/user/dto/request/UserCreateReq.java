package com.example.demo1.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class UserCreateReq {

    @Schema(description = "유저 이름")
    private String name;
}
