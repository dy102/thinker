package com.example.thinker.dto.response;

import com.example.thinker.dto.SurveyDto;

import java.util.List;

public record PremiumSurveysResponse(
        int premiumSurveysCount,
        List<SurveyDto> surveyDtos

) {
}
