package com.example.thinker.dto.request;

import com.example.thinker.dto.SurveyRequestDtos;

public record SurveyMakeRequest(
        boolean isPremium,
        String title,
        SurveyRequestDtos surveyRequestDtos
) {
}
