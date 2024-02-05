package com.example.thinker.dto;

import java.util.List;

public record SurveyDtos(List<MultipleChoiceDto> multipleChoiceDtos,
                         List<SubjectiveDto> subjectiveDtos) {
}
