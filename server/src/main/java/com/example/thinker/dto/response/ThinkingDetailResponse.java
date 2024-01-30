package com.example.thinker.dto.response;

import com.example.thinker.domain.Member;
import com.example.thinker.domain.Thinking;
import com.example.thinker.dto.ThinkingDetailDto;

public record ThinkingDetailResponse(
        boolean isManager,
        ThinkingDetailDto thinkingDetailDto) {
    public static ThinkingDetailResponse form(Thinking thinking, Member loginMember) {
        boolean isManager = false;
        if (loginMember.getGrade().equals("MANAGER")) {
            isManager = true;
        }
        return new ThinkingDetailResponse(isManager, ThinkingDetailDto.form(thinking));
    }
}
