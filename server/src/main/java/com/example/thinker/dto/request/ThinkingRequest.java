package com.example.thinker.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ThinkingRequest(List<MultipartFile> thinkingImage,
                              String thinkingTitle,
                              String thinkingContents,
                              boolean isPremium) {
}
