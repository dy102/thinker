package com.example.thinker.dto;

public record ReplyDto(
        Long thinkingId,
        Long replyId,
        Long memberId,
        String replyContents,
        Long likeCount,
        String createdAt,
        boolean isLiked,
        String who
) {
}
