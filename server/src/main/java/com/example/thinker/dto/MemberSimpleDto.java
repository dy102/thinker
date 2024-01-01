package com.example.thinker.dto;

import com.example.thinker.domain.Member;

public record MemberSimpleDto(String memberName,
                              String memberProfile,
                              String memberGrade) {
    public static MemberSimpleDto form(Member member) {
        return new MemberSimpleDto(member.getName(), member.getImageUrl(), member.getGrade().toString());
    }
}
