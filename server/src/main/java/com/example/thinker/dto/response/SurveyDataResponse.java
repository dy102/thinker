package com.example.thinker.dto.response;

import com.example.thinker.dto.SurveyDetailDto;

public record SurveyDataResponse(
        boolean isDone,
        boolean isOwner,
        boolean isManager,
        SurveyDetailDto surveyDetailDto
) {
}
