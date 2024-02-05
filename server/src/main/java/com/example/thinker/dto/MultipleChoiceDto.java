package com.example.thinker.dto;

import java.util.List;

public record MultipleChoiceDto(
        String question,
        List<String> items
) {
}
