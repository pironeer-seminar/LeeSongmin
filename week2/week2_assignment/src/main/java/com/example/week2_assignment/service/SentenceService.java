package com.example.week2_assignment.service;

import com.example.week2_assignment.dto.SentenceRequest;
import com.example.week2_assignment.dto.SentenceResponse;
import org.springframework.stereotype.Service;

@Service
public class SentenceService {

    public SentenceResponse analyze(SentenceRequest request) {
        String sentence = request.getSentence();

        int length = sentence.length();
        int wordCnt = sentence.trim().isEmpty() ? 0 : sentence.trim().split("\\s+").length;
        boolean containSpring = sentence.toLowerCase().contains("spring");

        return new SentenceResponse(length, wordCnt, containSpring);
    }
}
