package com.example.thinker.dto;

import java.util.List;

public record SurveyDetailDto(
        Long surveyId,
        String surveyTitle,
        List<MultipleChoiceDto> multipleChoiceDtos,
        List<SubjectiveDto> subjectiveDtos
) {
}
