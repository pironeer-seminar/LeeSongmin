package com.example.week2_assignment.controller;

import com.example.week2_assignment.dto.SentenceRequest;
import com.example.week2_assignment.dto.SentenceResponse;
import com.example.week2_assignment.service.SentenceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analyze")
public class SentenceController {
    private final SentenceService sentenceService;

    public SentenceController(SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    @PostMapping
    public ResponseEntity<SentenceResponse> analyze(@Valid @RequestBody SentenceRequest request) {
        SentenceResponse response = sentenceService.analyze(request);
        return ResponseEntity.ok(response);
    }
}
