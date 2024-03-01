package com.example.thinker.service;

import com.example.thinker.domain.Member;
import com.example.thinker.domain.Thinking;
import com.example.thinker.dto.ThinkingDtos;
import com.example.thinker.dto.request.ThinkingRequest;
import com.example.thinker.dto.response.PremiumThinkingResponse;
import com.example.thinker.dto.response.ThinkingDetailResponse;
import com.example.thinker.dto.response.ThinkingResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ThinkingService {
    PremiumThinkingResponse findPremiumThinkings(int page, int size);

    ThinkingResponse findThinkings(String kind, int size, Long lastId);

    Thinking makeThinking(List<MultipartFile> multipartFiles, ThinkingRequest thinkingRequest, Member loginMember) throws IOException;

    Thinking updateThinking(Long thinkingId, List<MultipartFile> multipartFiles, ThinkingRequest thinkingRequest, Member loginMember) throws IOException;

    ThinkingDetailResponse getThinkingDetail(Long thinkingId, Member loginMember);

    void deleteThinking(Member loginMember, Long thinkingId);

    ThinkingResponse findThinkingByTitle(String title, String kind, int size, Long lastId);

    ThinkingResponse findThinkingByContent(String content, String kind, int size, Long lastId);

    ThinkingResponse findThinkingByWriter(String writer, String kind, int size, Long lastId);

    ThinkingResponse getMemberActivityDetails(Member loginMember, Long memberId, int size, Long lastId);

    ThinkingDtos getMyThinkings(Member loginMember, String kind, int page, int size);

    ThinkingDtos getThinkingsByMyReply(Member loginMember, String kind, int page, int size);
}
