package com.example.week2_assignment.dto;

import jakarta.validation.constraints.NotBlank;

public class SentenceRequest {

    @NotBlank(message = "문장은 필수 입력값입니다.")
    private String sentence;

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
