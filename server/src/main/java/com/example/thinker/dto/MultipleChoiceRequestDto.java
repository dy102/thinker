package com.example.thinker.dto;

import java.util.List;

public record MultipleChoiceRequestDto(
        String question,
        List<String> items
) {
}
