package com.example.demo1.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserCreateReq {

    @NotNull
    @Schema(description = "유저 이름")
    private String name;
}
