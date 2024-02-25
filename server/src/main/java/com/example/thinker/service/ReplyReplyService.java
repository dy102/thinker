package com.example.thinker.service;

import com.example.thinker.domain.Member;
import com.example.thinker.dto.request.ContentsRequest;
import com.example.thinker.dto.response.RepliesResponse;

public interface ReplyReplyService {
    void postReplyReply(Member loginMember, ContentsRequest contentsRequest);

    void updateReplyReply(Member loginMember, ContentsRequest contentsRequest);

    RepliesResponse getReplyReplies(Member loginMember, Long thinkingId, Long replyId);

    void deleteReplyReply(Member loginMember, Long replyReplyId);

    boolean likeReplyReplies(Member loginMember, Long replyReplyId);
}
