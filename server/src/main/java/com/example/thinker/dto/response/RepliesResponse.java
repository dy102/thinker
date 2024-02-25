package com.example.thinker.dto.response;

import com.example.thinker.dto.ReplyDtos;

public record RepliesResponse(
        boolean isManager,
        ReplyDtos replyDtos
) {
}
