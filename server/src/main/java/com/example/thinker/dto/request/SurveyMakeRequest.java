package com.example.thinker.dto.request;

import com.example.thinker.dto.SurveyRequestDtos;
import org.springframework.web.multipart.MultipartFile;

public record SurveyMakeRequest(
        boolean isPremium,
        MultipartFile thumbnail,
        String title,
        SurveyRequestDtos surveyRequestDtos
) {
}
