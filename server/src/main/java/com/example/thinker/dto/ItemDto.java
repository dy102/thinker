package com.example.thinker.dto;

import com.example.thinker.domain.ChoiceForm;

public record ItemDto(
        Long itemId,
        String item,
        boolean isCheck
) {
    public static ItemDto form(ChoiceForm choiceForm, boolean isCheck) {
        return new ItemDto(choiceForm.getId(), choiceForm.getQuestion(), isCheck);
    }
}
