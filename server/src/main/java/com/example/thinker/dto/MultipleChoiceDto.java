package com.example.thinker.dto;

import com.example.thinker.domain.MultipleChoiceForm;

import java.util.List;

public record MultipleChoiceDto(
        Long multipleChoiceId,
        String question,
        List<ItemDto> items
) {
    public static MultipleChoiceDto form(MultipleChoiceForm multipleChoiceForm,
                                         List<ItemDto> items) {
        return new MultipleChoiceDto(multipleChoiceForm.getId(), multipleChoiceForm.getQuestion(), items);
    }
}
