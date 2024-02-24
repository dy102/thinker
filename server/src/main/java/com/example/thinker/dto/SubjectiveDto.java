package com.example.thinker.dto;

import com.example.thinker.domain.SubjectiveForm;

public record SubjectiveDto(
        Long subjectiveFormId,
        String question,
        String answer
) {
    public static SubjectiveDto form(SubjectiveForm subjectiveForm, String answer) {
        return new SubjectiveDto(subjectiveForm.getId(), subjectiveForm.getQuestion(), answer);
    }
}
