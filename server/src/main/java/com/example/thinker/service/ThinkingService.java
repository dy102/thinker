package com.example.thinker.service;

import com.example.thinker.domain.Member;
import com.example.thinker.domain.Thinking;
import com.example.thinker.dto.ThinkingDtos;
import com.example.thinker.dto.request.ThinkingRequest;
import com.example.thinker.dto.response.PremiumThinkingResponse;
import com.example.thinker.dto.response.ThinkingDetailResponse;
import com.example.thinker.dto.response.ThinkingResponse;

import java.io.IOException;

public interface ThinkingService {
    PremiumThinkingResponse findPremiumThinking(int page, int size);

    ThinkingResponse findThinking(String kind, int size, Long lastId);

    Thinking makeThinking(ThinkingRequest thinkingRequest, Member loginMember) throws IOException;

    Thinking updateThinking(Long thinkingId, ThinkingRequest thinkingRequest, Member loginMember) throws IOException;

    ThinkingDetailResponse getThinkingDetail(Long thinkingId, Member loginMember);

    void deleteThinking(Member loginMember, Long thinkingId);

    ThinkingResponse findThinkingByTitle(String title, String kind, int size, Long lastId);

    ThinkingResponse findThinkingByContent(String content, String kind, int size, Long lastId);

    ThinkingResponse findThinkingByWriter(String writer, String kind, int size, Long lastId);

    ThinkingResponse getMemberActivityDetails(Long memberId, int size, Long lastId);

    ThinkingDtos getMyThinkings(Member loginMember, String kind, int page, int size);

    ThinkingDtos getThinkingsByMyReply(Member loginMember, String kind, int page, int size);
}
