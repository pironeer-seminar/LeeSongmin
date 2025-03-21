package com.example.week2_assignment.dto;

public class SentenceResponse {
    private int length;
    private int wordCnt;
    private boolean containSpring;

    public SentenceResponse(int length, int wordCnt, boolean containSpring) {
        this.length = length;
        this.wordCnt = wordCnt;
        this.containSpring = containSpring;
    }

    public int getLength() {
        return length;
    }

    public int getWordCnt() {
        return wordCnt;
    }

    public boolean isContainSpring() {
        return containSpring;
    }
}
