package com.example.thinker.service;

import com.example.thinker.domain.Member;
import com.example.thinker.dto.request.MemberDataRequest;
import com.example.thinker.dto.response.MemberDataResponse;

public interface MemberService {
    Member create(MemberDataRequest memberDataRequest);

    MemberDataResponse read(Member loginMember);

    MemberDataResponse update(Member loginMember, MemberDataRequest memberDataRequest);

    Member login(String customId, String pw);
}
