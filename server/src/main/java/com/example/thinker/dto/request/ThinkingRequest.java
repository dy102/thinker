package com.example.thinker.dto.request;

public record ThinkingRequest(
        String thinkingTitle,
        String thinkingContents,
        boolean isPremium) {
}
