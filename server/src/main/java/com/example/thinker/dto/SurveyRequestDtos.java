package com.example.thinker.dto;

import java.util.List;

public record SurveyRequestDtos(List<MultipleChoiceRequestDto> multipleChoiceRequestDtos,
                                List<SubjectiveRequestDto> subjectiveRequestDtos) {
}
