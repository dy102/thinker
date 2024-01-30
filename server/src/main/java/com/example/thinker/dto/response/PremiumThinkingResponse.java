package com.example.thinker.dto.response;

import com.example.thinker.domain.Thinking;
import com.example.thinker.dto.ThinkingDtos;
import org.springframework.data.domain.Page;

import java.util.List;

public record PremiumThinkingResponse(int premiumThinkingCount,
                                      ThinkingDtos premiumThinkingDtos) {
    public static PremiumThinkingResponse form(Page<Thinking> thinkings) {
        List<Thinking> contents = thinkings.getContent();
        ThinkingDtos thinkingDtos = ThinkingDtos.form(contents);
        return new PremiumThinkingResponse(thinkingDtos.dtos().size(), thinkingDtos);
    }
}
