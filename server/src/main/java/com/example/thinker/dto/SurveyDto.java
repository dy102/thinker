package com.example.thinker.dto;

import java.util.List;

public record SurveyDto(
        Long surveyId,
        List<MultipleChoiceDto> multipleChoiceDtos,
        List<SubjectiveDto> subjectiveDtos
) {
}
