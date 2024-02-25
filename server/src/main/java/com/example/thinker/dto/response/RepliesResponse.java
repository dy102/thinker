package com.example.thinker.dto.response;

import com.example.thinker.dto.TotalReplyDtos;

public record RepliesResponse(
        boolean isManager,
        TotalReplyDtos toTalReplyDtos
) {
}
