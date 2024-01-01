package com.example.thinker.dto.response;

import com.example.thinker.domain.Grade;
import com.example.thinker.domain.Member;

public record MemberDataResponse(String customId, String pw,
                                 String nickname,
                                 String birthday,
                                 String gender,
                                 Long point,
                                 Grade grade) {
    public static MemberDataResponse form(Member member) {
        return new MemberDataResponse(member.getCustomId(), member.getPw(),
                member.getName(), member.getBirthday().toString(),
                member.getGender(), member.getPoint(), member.getGrade());
    }
}
