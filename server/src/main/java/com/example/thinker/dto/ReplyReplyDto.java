package com.example.thinker.dto;

public record ReplyReplyDto(
        Long replyId,
        Long replyReplyId,
        Long memberId,
        String replyReplyContents,
        Long likeCount,
        String createdAt,
        boolean isLiked,
        String who
) implements ReplyDtoBase {

}
