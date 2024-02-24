package com.example.thinker.dto;

import com.example.thinker.domain.Survey;

public record SurveyDto(
        Long surveyId,
        byte[] surveyImage,
        String surveyWriter,
        String surveyTitle,
        Long surveyItemCount,
        boolean isDone,
        boolean isPremium
) {

    public static SurveyDto form(Survey survey, boolean isDone) {
        return new SurveyDto(survey.getId(), survey.getImage().getData(),
                survey.getWriter().getName(), survey.getTitle(),
                (long) (survey.getMultipleChoiceForms().size() + survey.getSubjectiveForms().size()),
                isDone, survey.getIsPremium());
    }
}
