package com.example.thinker.dto;

import com.example.thinker.domain.Thinking;

import java.util.ArrayList;
import java.util.List;

public record ThinkingDtos(List<ThinkingDto> dtos) {
    public static ThinkingDtos form(List<Thinking> thinkings) {
        List<ThinkingDto> dtos = new ArrayList<>();
        for (Thinking thinking : thinkings) {
            ThinkingDto dto = ThinkingDto.form(thinking);
            dtos.add(dto);
        }
        return new ThinkingDtos(dtos);
    }
}
