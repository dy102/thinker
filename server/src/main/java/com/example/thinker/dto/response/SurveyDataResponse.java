package com.example.thinker.dto.response;

import com.example.thinker.dto.SurveyDto;

public record SurveyDataResponse(
        boolean isDone,
        boolean isManager,
        SurveyDto surveyDto
) {
}
