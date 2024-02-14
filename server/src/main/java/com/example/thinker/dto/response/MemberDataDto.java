package com.example.thinker.dto.response;

import com.example.thinker.domain.Member;

public record MemberDataDto(String customId, String pw,
                            String nickname,
                            String birthday,
                            String gender,
                            Long point,
                            String grade) {
    public static MemberDataDto form(Member member) {
        return new MemberDataDto(member.getCustomId(), member.getPw(),
                member.getName(), member.getBirthday().toString(),
                member.getGender(), member.getPoint(), member.getGrade());
    }
}
