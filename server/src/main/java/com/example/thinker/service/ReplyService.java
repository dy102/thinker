package com.example.thinker.service;

import com.example.thinker.domain.Member;
import com.example.thinker.dto.response.RepliesResponse;

public interface ReplyService {
    void postReplies(Member loginMember, Long thinkingId, String replyContent);

    RepliesResponse getReplies(Member loginMember, Long thinkingId);

    void updateReplies(Member loginMember, Long replyId, String replyContent);

    void deleteReplies(Member loginMember, Long replyId);

    boolean likeReplies(Member loginMember, Long replyId);

    RepliesResponse findRepliesByMember(Member loginMember, Long memberId);
}
