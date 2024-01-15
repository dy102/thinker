package com.example.thinker.service;

import com.example.thinker.domain.Member;
import com.example.thinker.dto.MemberSimpleDto;
import com.example.thinker.dto.request.MemberDataRequest;
import com.example.thinker.dto.response.MemberDataDto;

public interface MemberService {
    Member create(MemberDataRequest memberDataRequest);

    MemberDataDto read(Member loginMember);

    MemberSimpleDto readSimple(Long memberId);

    void update(Member loginMember, MemberDataRequest memberDataRequest);

    Member login(String customId, String pw);
}
